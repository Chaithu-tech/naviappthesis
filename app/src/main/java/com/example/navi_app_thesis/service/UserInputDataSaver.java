package com.example.navi_app_thesis.service;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserInputDataSaver {

    private static final String TAG = "UserInputDataSaver";


    public static void saveUserInputDataToAppSpecificFile(Context context, UserInputData userInputData) {

        String formattedDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String fileName = "mission_" + formattedDate + ".txt";


        String fileContent = userInputData.toString();


        File directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (directory == null) {
            Log.e(TAG, "Error getting external files directory");
            Toast.makeText(context, "Error accessing external storage!", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(directory, fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {

            fos.write(fileContent.getBytes());
            fos.flush();


            Toast.makeText(context, "Data saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Log.d("FilePath", "File saved to: " + directory.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "Error saving file: " + e.getMessage(), e);
            Toast.makeText(context, "Error saving data!", Toast.LENGTH_SHORT).show();
        }
    }


    public static void saveUserInputDataToFile(Context context, UserInputData userInputData) {

        String formattedDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String fileName = "mission_" + formattedDate + ".txt";


        String fileContent = userInputData.toString();


        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {

            fos.write(fileContent.getBytes());
            fos.flush();


            Toast.makeText(context, "Data saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e(TAG, "Error saving file: " + e.getMessage(), e);
            Toast.makeText(context, "Error saving data!", Toast.LENGTH_SHORT).show();
        }
    }
}