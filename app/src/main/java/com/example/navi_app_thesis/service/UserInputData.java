package com.example.navi_app_thesis.service;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserInputData {

    private String date;
    private float focalLength;
    private float sensorHeight;
    private float sensorWidth;
    private double easting;  // Easting coordinate
    private double northing; // Northing coordinate

    // Updated constructor to accept Easting and Northing
    public UserInputData(String date, float focalLength, float sensorHeight, float sensorWidth, double easting, double northing) {
        this.date = date;
        this.focalLength = focalLength;
        this.sensorHeight = sensorHeight;
        this.sensorWidth = sensorWidth;
        this.easting = easting;
        this.northing = northing;
    }

    // Getter and Setter for Easting
    public double getEasting() {
        return easting;
    }

    public void setEasting(double easting) {
        this.easting = easting;
    }

    // Getter and Setter for Northing
    public double getNorthing() {
        return northing;
    }

    public void setNorthing(double northing) {
        this.northing = northing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(float focalLength) {
        this.focalLength = focalLength;
    }

    public float getSensorHeight() {
        return sensorHeight;
    }

    public void setSensorHeight(float sensorHeight) {
        this.sensorHeight = sensorHeight;
    }

    public float getSensorWidth() {
        return sensorWidth;
    }

    public void setSensorWidth(float sensorWidth) {
        this.sensorWidth = sensorWidth;
    }

    // current date in German format
    public static String getCurrentDateInGermanFormat() {
        return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(new Date());
    }

    // Override toString to include Easting and Northing
    @NonNull
    @Override
    public String toString() {
        return "Date: " + getDate() + "\nFocal Length: " + getFocalLength() + " mm\nSensor Height: "
                + getSensorHeight() + " px\nSensor Width: " + getSensorWidth() + " px\nEasting: "
                + getEasting() + "\nNorthing: " + getNorthing();
    }
}
