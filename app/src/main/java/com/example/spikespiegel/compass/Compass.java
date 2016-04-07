package com.example.spikespiegel.compass;


    import android.hardware.Sensor;
    import android.hardware.SensorEvent;
    import android.hardware.SensorManager;
    import android.os.Bundle;
    import android.app.Activity;
    import android.support.design.widget.FloatingActionButton;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.View;
    import android.hardware.SensorEventListener;
    import android.view.animation.Animation;
    import android.view.animation.RotateAnimation;
    import android.widget.ImageView;
    import android.widget.TextView;

    public class Compass extends Activity implements SensorEventListener{
        private ImageView image;
        private float currentDegree = 0f;
        private SensorManager sensorManager;
        TextView heading;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_compass);


            image = (ImageView)findViewById(R.id.imageViewCompass);
            heading = (TextView)findViewById(R.id.heading);
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float degree = Math.round(event.values[0]);
            heading.setText("Direction: " + Float.toString(degree) + " degrees");

            RotateAnimation rAnimation = new RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rAnimation.setDuration(200);
            rAnimation.setFillAfter(true);
            image.startAnimation(rAnimation);
            currentDegree = -degree;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        protected void onPause(){
            super.onPause();

            sensorManager.unregisterListener(this);
        }
        @Override

        protected void onResume(){
            super.onResume();
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);


        }
    }

