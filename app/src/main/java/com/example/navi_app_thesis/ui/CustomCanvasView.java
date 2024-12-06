package com.example.navi_app_thesis.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import com.example.navi_app_thesis.MainActivity;


public class CustomCanvasView extends View {

    private Paint paint;
    private Button buttonLeft, buttonRight, buttonSlower, buttonFaster, buttonHigher, buttonLower, buttonQuit;


    private CustomCanvasView(Builder builder) {
        super(builder.context);
        this.buttonLeft = builder.buttonLeft;
        this.buttonRight = builder.buttonRight;
        this.buttonSlower = builder.buttonSlower;
        this.buttonFaster = builder.buttonFaster;
        this.buttonHigher = builder.buttonHigher;
        this.buttonLower = builder.buttonLower;
        this.buttonQuit = builder.buttonQuit;
        init();
        setupButtonHandlers(this);
    }

    public CustomCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
    }


    private void setupButtonHandlers(CustomCanvasView customCanvasView) {
        if (buttonLeft != null) {
            buttonLeft.setOnClickListener(v -> {

            });
        }

        if (buttonRight != null) {
            buttonRight.setOnClickListener(v -> {

            });
        }

        if (buttonSlower != null) {
            buttonSlower.setOnClickListener(v -> {

            });
        }

        if (buttonFaster != null) {
            buttonFaster.setOnClickListener(v -> {

            });
        }

        if (buttonHigher != null) {
            buttonHigher.setOnClickListener(v -> {

            });
        }

        if (buttonLower != null) {
            buttonLower.setOnClickListener(v -> {

            });
        }

        if (buttonQuit != null) {
            buttonQuit.setOnClickListener(v -> {
                if (getContext() instanceof MainActivity) {
                    ((MainActivity) getContext()).finish();
                }
            });
        }
    }


    public static class Builder {
        private final Context context;
        private Button buttonLeft, buttonRight, buttonSlower, buttonFaster, buttonHigher, buttonLower, buttonQuit;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setButtonLeft(Button buttonLeft) {
            this.buttonLeft = buttonLeft;
            return this;
        }

        public Builder setButtonRight(Button buttonRight) {
            this.buttonRight = buttonRight;
            return this;
        }

        public Builder setButtonSlower(Button buttonSlower) {
            this.buttonSlower = buttonSlower;
            return this;
        }

        public Builder setButtonFaster(Button buttonFaster) {
            this.buttonFaster = buttonFaster;
            return this;
        }

        public Builder setButtonHigher(Button buttonHigher) {
            this.buttonHigher = buttonHigher;
            return this;
        }

        public Builder setButtonLower(Button buttonLower) {
            this.buttonLower = buttonLower;
            return this;
        }

        public Builder setButtonQuit(Button buttonQuit) {
            this.buttonQuit = buttonQuit;
            return this;
        }

        public CustomCanvasView build() {

            return new CustomCanvasView(this);
        }
    }
}
