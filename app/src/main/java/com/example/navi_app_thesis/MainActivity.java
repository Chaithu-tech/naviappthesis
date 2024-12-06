package com.example.navi_app_thesis;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navi_app_thesis.service.UserInputData;
import com.example.navi_app_thesis.service.UserInputDataSaver;
import com.example.navi_app_thesis.ui.CanvasActivity;

public class MainActivity extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final String TAG = "MainActivity";

    private EditText etDate, etFocalLength, etSensorHeight, etSensorWidth, etEasting, etNorthing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        etDate = findViewById(R.id.etDate);
        etFocalLength = findViewById(R.id.etFocalLength);
        etSensorHeight = findViewById(R.id.etSensorHeight);
        etSensorWidth = findViewById(R.id.etSensorWidth);
        etEasting = findViewById(R.id.etEasting); // Easting input
        etNorthing = findViewById(R.id.etNorthing); // Northing input
        Button btnSave = findViewById(R.id.btnSave);
        Button btnStart = findViewById(R.id.btnStart);

        // Set current date in the input field
        etDate.setText(UserInputData.getCurrentDateInGermanFormat());

        // Save button functionality
        btnSave.setOnClickListener(view -> saveData(etDate, etFocalLength, etSensorHeight, etSensorWidth, etEasting, etNorthing));

        // Start button functionality: pass data to CanvasActivity
        btnStart.setOnClickListener(view -> {
            try {
                // Get user input values
                double focalLength = Double.parseDouble(etFocalLength.getText().toString());
                double sensorHeight = Double.parseDouble(etSensorHeight.getText().toString());
                double sensorWidth = Double.parseDouble(etSensorWidth.getText().toString());
                double easting = Double.parseDouble(etEasting.getText().toString()); // Easting value
                double northing = Double.parseDouble(etNorthing.getText().toString()); // Northing value

                // Create an intent to start CanvasActivity and pass the data
                Intent intent = new Intent(MainActivity.this, CanvasActivity.class);
                intent.putExtra("focalLength", focalLength);
                intent.putExtra("sensorHeight", sensorHeight);
                intent.putExtra("sensorWidth", sensorWidth);
                intent.putExtra("easting", easting); // Pass Easting
                intent.putExtra("northing", northing); // Pass Northing

                // Start CanvasActivity
                startActivity(intent);
            } catch (NumberFormatException e) {
                // Show error if input is invalid
                Toast.makeText(this, "Please enter valid numeric values!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveData(EditText etDate, EditText etFocalLength, EditText etSensorHeight, EditText etSensorWidth, EditText etEasting, EditText etNorthing) {
        try {
            // Collect the user input from the EditText fields
            String date = etDate.getText().toString();
            float focalLength = Float.parseFloat(etFocalLength.getText().toString());
            float sensorHeight = Float.parseFloat(etSensorHeight.getText().toString());
            float sensorWidth = Float.parseFloat(etSensorWidth.getText().toString());
            double easting = Double.parseDouble(etEasting.getText().toString());  // Easting value
            double northing = Double.parseDouble(etNorthing.getText().toString());  // Northing value

            // Create a UserInputData object with the user input (including Easting and Northing)
            UserInputData userInputData = new UserInputData(date, focalLength, sensorHeight, sensorWidth, easting, northing);

            // Check if the app is running on Android 10 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Use app-specific directory for Android 10 and higher
                UserInputDataSaver.saveUserInputDataToAppSpecificFile(this, userInputData);
            } else {
                // Use public Documents directory for Android 9 and below
                UserInputDataSaver.saveUserInputDataToFile(this, userInputData);
            }

            // Save logic here...
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            // Show error if input is invalid
            Toast.makeText(this, "Please enter valid numeric values!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
