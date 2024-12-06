package com.example.navi_app_thesis.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GroundCoverageView extends View {

    private Paint paintRed;
    private Paint paintBlack;
    private Paint textPaint;  // For drawing text
    private double groundWidth;
    private double groundHeight;

    private final List<Overlay> overlays = new ArrayList<>();

    // Constructor
    public GroundCoverageView(Context context) {
        super(context);
        init();
    }

    public GroundCoverageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        paintRed = new Paint();
        paintRed.setColor(Color.RED);
        paintRed.setStrokeWidth(5);
        paintRed.setStyle(Paint.Style.STROKE);


        paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStrokeWidth(3);
        paintBlack.setStyle(Paint.Style.STROKE);


        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(36);
    }


    public void setGroundCoverage(double width, double height) {
        this.groundWidth = width;
        this.groundHeight = height;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int padding = 50;
        float availableWidth = getWidth() - (2 * padding);
        float availableHeight = getHeight() - (2 * padding);


        float scaleX = (float) (availableWidth / groundWidth);
        float scaleY = (float) (availableHeight / groundHeight);
        float scale = Math.min(scaleX, scaleY);


        float actualWidth = (float) (groundWidth * scale);
        float actualHeight = (float) (groundHeight * scale);


        canvas.drawRect(padding, padding, padding + actualWidth, padding + actualHeight, paintRed);


        float bottom = getHeight() - padding;
        for (int i = 0; i < overlays.size(); i++) {
            Overlay currentOverlay = overlays.get(i);


            if (i == overlays.size() - 1) {
                currentOverlay.updatePosition();
            }


            drawBlackRectangle(canvas, padding + currentOverlay.getPosX(), bottom - currentOverlay.getPosY(), actualWidth, actualHeight, scale, currentOverlay);
        }


        if (overlays.size() >= 2) {
            String percentages = OverlayPercentageCalculator.calculate(overlays.get(overlays.size() - 2), overlays.get(overlays.size() - 1));


            float textWidth = textPaint.measureText(percentages);
            float textX = Math.min(getWidth() - padding - textWidth, padding + actualWidth - textWidth); // Make sure it stays inside the boundary
            float textY = getHeight() - padding - 20; // Adjust Y to stay within the boundary

            canvas.drawText(percentages, textX, textY, textPaint);
        }
    }

    // Method to draw the black rectangle inside the red rectangle
    private void drawBlackRectangle(Canvas canvas, float left, float bottom, float actualWidth, float actualHeight, float scale, Overlay overlay) {
        // Apply the scale to fit the black rectangle inside the red rectangle
        float scaledImageWidth = (float) (overlay.getImageWidth() * scale);  // Scale the width
        float scaledImageHeight = (float) (overlay.getImageHeight() * scale);  // Scale the height

        // Ensure the black rectangle fits within the red one
        scaledImageWidth = Math.min(scaledImageWidth, actualWidth);
        scaledImageHeight = Math.min(scaledImageHeight, actualHeight);

        // Calculate the rectangle's top (since it's drawn from the bottom)
        float top = bottom - scaledImageHeight;

        // Draw the black rectangle within the red rectangle bounds
        canvas.drawRect(left, top, left + scaledImageWidth, bottom, paintBlack);

        // Log the rectangle placement for debugging
        Log.d("GroundCoverageView", "Black rectangle drawn from (" + left + ", " + top + ") to (" + (left + scaledImageWidth) + ", " + bottom + ")");
    }

    // Method to add a new overlay to the list with speed and position
    public void addOverlay(double imageWidth, double imageHeight, double speed) {
        float startX = 0;
        float startY = 0;

        // If there's already an overlay, inherit the last one's position
        if (!overlays.isEmpty()) {
            Overlay lastOverlay = overlays.get(overlays.size() - 1); // Get the last overlay
            startX = lastOverlay.getPosX(); // Set the starting X position to the last overlay's final X
            startY = lastOverlay.getPosY(); // Set the starting Y position to the last overlay's final Y
        }

        // Add the new overlay with the starting position inherited from the last one
        Overlay newOverlay = new Overlay(imageWidth, imageHeight, speed, startX, startY);
        overlays.add(newOverlay); // Add the new overlay to the list
        invalidate();  // Request to redraw the view with the new overlay
    }

    // Method to clear all overlays
    public void clearOverlays() {
        overlays.clear(); // Clear all overlays
    }
}