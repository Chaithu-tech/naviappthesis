package com.example.navi_app_thesis.service;

public class ImageBuilder {


    public static double calculateImageHeight(double focalLength, double sensorHeight, double pixelSize, double cameraHeight, double cameraAngle, double speed) {

        double sensorHeightMm = sensorHeight * pixelSize;

        double cameraAngleRadians = Math.toRadians(cameraAngle);

        double verticalFoV = 2 * Math.atan(sensorHeightMm / (2 * focalLength));

        return 2 * (cameraHeight * Math.tan(verticalFoV / 2)) * Math.cos(cameraAngleRadians);
    }



    public static double calculateImageWidth(double focalLength, double sensorWidth, double pixelSize, double cameraHeight, double cameraAngle, double speed) {

        double sensorWidthMm = sensorWidth * pixelSize;

        double cameraAngleRadians = Math.toRadians(cameraAngle);

        double horizontalFoV = 2 * Math.atan(sensorWidthMm / (2 * focalLength));

        return 2 * (cameraHeight * Math.tan(horizontalFoV / 2)) * Math.sin(cameraAngleRadians);
    }
}
