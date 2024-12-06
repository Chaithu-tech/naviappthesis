package com.example.navi_app_thesis.ui;

import static com.example.navi_app_thesis.service.ImageBuilder.calculateImageHeight;
import static com.example.navi_app_thesis.service.ImageBuilder.calculateImageWidth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navi_app_thesis.R;
import com.example.navi_app_thesis.view.GroundCoverageView;

public class CanvasActivity extends AppCompatActivity {

    private static final String TAG = "CanvasActivity";

    private double currentEast;  // Easting coordinate
    private double currentNorth; // Northing coordinate

    private double cameraHeight = 40;
    private double cameraAngle = 45;
    private double speed = 10;
    private final double pixelSize = 5;

    double focalLength;
    double sensorHeight;
    double sensorWidth;

    GroundCoverageView groundCoverageView;
    TextView infoTextView;
    TextView eastingTextView, northingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        // Get initial UTM coordinates passed from MainActivity
        currentEast = getIntent().getDoubleExtra("easting", 393000);  // Default is Berlin UTM East
        currentNorth = getIntent().getDoubleExtra("northing", 6295000); // Default is Berlin UTM North

        double groundWidth = 1050;
        double groundHeight = 450;

        // Initialize UI components
        Button buttonLeft = findViewById(R.id.leftBtn);
        Button buttonRight = findViewById(R.id.rightBtn);
        Button buttonSlower = findViewById(R.id.slowerBtn);
        Button buttonFaster = findViewById(R.id.fasterBtn);
        Button buttonHigher = findViewById(R.id.higherBtn);
        Button buttonLower = findViewById(R.id.lowerBtn);
        Button buttonQuit = findViewById(R.id.quitBtn);
        Button buttonRestart = findViewById(R.id.restartBtn);

        groundCoverageView = findViewById(R.id.groundCoverageView);
        groundCoverageView.setGroundCoverage(groundWidth, groundHeight);

        infoTextView = findViewById(R.id.infoTextView);
        eastingTextView = findViewById(R.id.eastingTextView);  // For displaying Easting
        northingTextView = findViewById(R.id.northingTextView);  // For displaying Northing

        // Set initial coordinates
        updateInfoText();

        // Set button listeners
        buttonLeft.setOnClickListener(v -> {
            cameraAngle = adjustValue(cameraAngle, -1);
            moveCamera(-speed, cameraAngle - 90);
            updateInfoText();
        });

        buttonRight.setOnClickListener(v -> {
            cameraAngle = adjustValue(cameraAngle, 1);
            moveCamera(speed, cameraAngle + 90);
            updateInfoText();
        });

        buttonHigher.setOnClickListener(v -> {
            moveCamera(speed, cameraAngle);
            cameraHeight = adjustValue(cameraHeight, 5);
            updateInfoText();
        });

        buttonLower.setOnClickListener(v -> {
            if (cameraHeight <= 20) {
                return; // Prevent further lowering when the height is 20 or below
            }
            moveCamera(-speed, cameraAngle + 180);
            cameraHeight = Math.max(cameraHeight - 5, 20);
            updateInfoText();
        });

        buttonFaster.setOnClickListener(v -> {
            speed = Math.max(speed + 10, 5);
            updateInfoText();
        });

        buttonSlower.setOnClickListener(v -> {
            speed = Math.max(speed - 10, 5);
            updateInfoText();
        });

        buttonQuit.setOnClickListener(v -> finishAffinity());

        buttonRestart.setOnClickListener(v -> {
            restartCanvas();
        });

        focalLength = getIntent().getDoubleExtra("focalLength", 2.5);
        sensorHeight = getIntent().getDoubleExtra("sensorHeight", 4000);
        sensorWidth = getIntent().getDoubleExtra("sensorWidth", 3000);

        updateImageDimensions();
    }

    private double adjustValue(double value, int delta) {
        return value + delta;
    }

    private void moveCamera(double distance, double angle) {
        // Convert angle to radians
        double angleInRadians = Math.toRadians(angle);

        // Calculate the changes in Easting and Northing based on angle and distance (delta)
        double deltaEast = distance * Math.cos(angleInRadians);  // Eastward (X)
        double deltaNorth = distance * Math.sin(angleInRadians); // Northward (Y)

        // Update the current position
        currentEast += deltaEast;
        currentNorth += deltaNorth;

        updatePositionAndInfo();
    }

    private void updatePositionAndInfo() {
        updateImageDimensions();
        updateInfoText();
    }

    private void updateImageDimensions() {
        double imageWidth = calculateImageWidth(focalLength, sensorWidth, pixelSize, cameraHeight, cameraAngle, speed);
        double imageHeight = calculateImageHeight(focalLength, sensorHeight, pixelSize, cameraHeight, cameraAngle, speed);

        Log.d(TAG, "Calculated Image Width: " + imageWidth + " meters");
        Log.d(TAG, "Calculated Image Height: " + imageHeight + " meters");

        groundCoverageView.addOverlay(imageWidth / 2000, imageHeight / 2000, speed);
    }

    private void restartCanvas() {
        groundCoverageView.clearOverlays();

        cameraHeight = 40;
        cameraAngle = 45;
        speed = 10;

        currentEast = 393000;
        currentNorth = 6295000;

        updateImageDimensions();
        updateInfoText();

        groundCoverageView.invalidate();
    }

    private void updateInfoText() {
        @SuppressLint("DefaultLocale") String info = String.format("Height: %.2f\nSpeed: %.2f\nTrack: %.2f", cameraHeight, speed, cameraAngle);
        infoTextView.setText(info);

        // Update UTM coordinates display
        eastingTextView.setText(String.format("Easting: %.2f", currentEast));
        northingTextView.setText(String.format("Northing: %.2f", currentNorth));
    }
}
