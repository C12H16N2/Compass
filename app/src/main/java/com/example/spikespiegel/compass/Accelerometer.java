package com.example.spikespiegel.compass;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.os.Bundle;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private TextView x, y, z;
    private Sensor sensor;
    private SensorManager sensorManager;
    float[] accSensorValues;
    float MIN = 0.3f;

    @Override
    public void onSensorChanged(SensorEvent event) {
        accSensorValues = lowPass(event.values.clone(), accSensorValues);
        x.setText("X: " + event.values[0]);
        y.setText("Y: " + event.values[1]);
        z.setText("Z: " + event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews
        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);


    }

    protected float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + MIN * (input[i] - output[i]);
        }
        return output;
    }



}
