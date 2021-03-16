package com.amanda.customviewexploration;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CompassViewJava extends ConstraintLayout {

    private final String TAG = "Compass_View_Java";
    private Float currentAlpha = 0f;

    private ImageView compassNeedle = null;

    public CompassViewJava(@NonNull Context context) {
        super(context);
        Log.d(TAG, "CompassViewJava(context) called");
        inflate(context, R.layout.compass_view, this);
        Log.d(TAG, "Inflation started.");

    }

    public CompassViewJava(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CompassViewJava(context, attrs) called");
        inflate(context, R.layout.compass_view, this);
        Log.d(TAG, "Inflation started.");
    }

    public CompassViewJava(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.compass_view, this);
        Log.d(TAG, "Inflation started.");
    }

    public CompassViewJava(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.compass_view, this);
        Log.d(TAG, "Inflation started.");
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate() called.");
        compassNeedle = findViewById(R.id.compass_needle);
    }

    public void adjustArrow(Float alpha) {
        Animation animation = new RotateAnimation(-currentAlpha, -alpha,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        currentAlpha = alpha;

        animation.setDuration(500);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        compassNeedle.startAnimation(animation);
    }
}
