package com.amanda.customviewexploration

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class CompassView : ConstraintLayout {
    private var currentAlpha = 0f
    private var compassNeedle: ImageView? = null

    constructor(context: Context) : super(context) {
        Log.d(TAG, "CompassView(context) called")
        View.inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        Log.d(TAG, "CompassView(context, attrs) called")
        View.inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        Log.d(TAG, "CompassView(context, attrs, defStyleAttr) called")
        View.inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        View.inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    init {
        Log.d(TAG, "Kotlin init block called.")
    }

    public override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate() called.")
        compassNeedle = findViewById(R.id.compass_needle)
    }

    fun adjustArrow(alpha: Float) {
        val animation: Animation = RotateAnimation(
            -currentAlpha, -alpha,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        currentAlpha = alpha
        animation.duration = 500
        animation.repeatCount = 0
        animation.fillAfter = true
        compassNeedle?.startAnimation(animation)
    }

    companion object {
        private const val TAG = "Compass_View_Kotlin"
    }
}