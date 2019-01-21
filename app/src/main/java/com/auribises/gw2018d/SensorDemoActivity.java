package com.auribises.gw2018d;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SensorDemoActivity extends AppCompatActivity
    implements View.OnClickListener, SensorEventListener{

    TextView txtData;
    Button btnActivate;

    SensorManager sensorManager;
    Sensor sensor;

    void initViews(){
        txtData = findViewById(R.id.textViewData);
        btnActivate = findViewById(R.id.buttonActivate);
        btnActivate.setOnClickListener(this);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_demo);
    }

    @Override
    public void onClick(View v) {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        // Capturing Shake Event
        float cal = ((x*x)+(y*y)+(z*z)) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        if(cal>2){
            txtData.setText("Device Shaken !!");
            sensorManager.unregisterListener(this);
            // Send SMS to Home for Emergency !!
        }else{
            txtData.setText(x+" : "+y+" : "+z);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
