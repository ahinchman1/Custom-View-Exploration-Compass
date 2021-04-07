package com.amanda.customviewexploration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.*
import androidx.constraintlayout.widget.ConstraintSet
import com.amanda.customviewexploration.databinding.ActivityMainBinding

/**
 * Credit to @iutinvg for the simple Android compass implementation
 * https://github.com/iutinvg/compass
 */

class MainActivity : AppCompatActivity() {

    private var compass: Compass? = null
    private var compassView: CompassView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        compassView = CompassView(this)

        setContentView(binding.root)
        setupCompass()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart(): Start compass.")
        compass?.start()
    }

    override fun onPause() {
        super.onPause()
        compass?.stop()
    }

    private fun setupCompass() {
        compass = Compass(this)
        val cl = getCompassListener()
        compass?.setListener(cl)
    }

    private fun setItemConstraints(constraintLayout: ConstraintLayout, compassViewId: Int, endViewId: Int) {
        ConstraintSet().run {
            clone(constraintLayout)
            connect(compassViewId, START, PARENT_ID, START)
            connect(compassViewId, END, PARENT_ID, END)
            connect(compassViewId, TOP, endViewId, BOTTOM)
            connect(compassViewId, BOTTOM, PARENT_ID, BOTTOM)
        }
    }

    override fun onResume() {
        super.onResume()
        compass?.start()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop(): Stop compass.")
        compass?.stop()
    }

    private fun getCompassListener(): Compass.CompassListener? {
        return object : Compass.CompassListener {
            override fun onNewAlpha(alpha: Float) {
                Thread(Runnable {
                    compassView?.adjustArrow(alpha)
                }).start()
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }

}