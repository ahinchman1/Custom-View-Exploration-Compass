package com.amanda.customviewexploration

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.amanda.customviewexploration.databinding.CompassViewBinding

/**
 * Credit to @iutinvg for the simple Android compass implementation
 * https://github.com/iutinvg/compass
 */

class CompassView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CompassViewBinding.inflate(LayoutInflater.from(context))

    private var currentAlpha = 0f

    init {
        Log.d(TAG, "Kotlin init block called.")
        inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "YEP.")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate() called.")
    }

    fun adjustArrow(alpha: Float) {
        val an: Animation = RotateAnimation(
            -currentAlpha,
            -alpha,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        currentAlpha = alpha

        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true

        binding.compassNeedle.startAnimation(an)
    }

    companion object {
        const val TAG = "Compass View"
    }
}