package com.amanda.customviewexploration

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * Credit to @iutinvg for the simple Android compass implementation
 * https://github.com/iutinvg/compass
 */

class Compass(context: Context) : SensorEventListener {

    private var listener: CompassListener? = null
    private val sensorManager: SensorManager = context
        .getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gSensor: Sensor
    private val mSensor: Sensor
    private val mGravity = FloatArray(3)
    private val mGeomagnetic = FloatArray(3)
    private val r = FloatArray(9)
    private val i = FloatArray(9)
    private var alpha = 0f
    private var alphaFix = 0f

    interface CompassListener {
        fun onNewAlpha(alpha: Float)
    }

    init {
        gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    fun start() {
        sensorManager.registerListener(this, gSensor, SensorManager.SENSOR_DELAY_GAME)
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    fun setListener(l: CompassListener?) {
        listener = l
    }

    override fun onSensorChanged(event: SensorEvent) {
        val alpha = 0.97f
        synchronized(this) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * event.values[0]
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * event.values[1]
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * event.values[2]
            }

            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * event.values[0]
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * event.values[1]
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * event.values[2]
            }
            val success = SensorManager.getRotationMatrix(r, i, mGravity, mGeomagnetic)

            if (success) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(r, orientation)

                // orientation
                this.alpha = Math.toDegrees(orientation[0].toDouble()).toFloat()
                this.alpha = (this.alpha + alphaFix + 360) % 360
                listener?.onNewAlpha(this.alpha)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) { /* Do nothing */ }
}
