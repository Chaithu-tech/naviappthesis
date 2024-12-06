package com.example.navi_app_thesis.view;

import android.annotation.SuppressLint;

public class OverlayPercentageCalculator {

    @SuppressLint("DefaultLocale")
    public static String calculate(Overlay secondLastOverlay, Overlay lastOverlay) {
        // Horizontal overlap
        double left1 = secondLastOverlay.getPosX();
        double right1 = left1 + secondLastOverlay.getImageWidth();
        double left2 = lastOverlay.getPosX();
        double right2 = left2 + lastOverlay.getImageWidth();
        double horizontalOverlap = Math.max(0, Math.min(right1, right2) - Math.max(left1, left2));
        double horizontalPercentage = (horizontalOverlap / secondLastOverlay.getImageWidth()) * 100;

        // Vertical overlap
        double top1 = secondLastOverlay.getPosY();
        double bottom1 = top1 + secondLastOverlay.getImageHeight();
        double top2 = lastOverlay.getPosY();
        double bottom2 = top2 + lastOverlay.getImageHeight();
        double verticalOverlap = Math.max(0, Math.min(bottom1, bottom2) - Math.max(top1, top2));
        double verticalPercentage = (verticalOverlap / secondLastOverlay.getImageHeight()) * 100;

        return String.format("Overlay Horizontal: %.2f%% and Vertical: %.2f%%", horizontalPercentage, verticalPercentage);
    }
}
