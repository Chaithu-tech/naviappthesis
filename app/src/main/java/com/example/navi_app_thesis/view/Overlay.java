package com.example.navi_app_thesis.view;

public class Overlay {
    private final double imageWidth;
    private final double imageHeight;
    private float posX;
    private float posY;
    private final double speed;

    public Overlay(double imageWidth, double imageHeight, double speed, float startX, float startY) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.speed = speed;
        this.posX = startX; // Start position in X
        this.posY = startY; // Start position in Y
    }

    // Update the position of the overlay based on speed
    public void updatePosition() {
        posX += (float) speed;
        posY += (float) speed;
    }

    // Getters
    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
