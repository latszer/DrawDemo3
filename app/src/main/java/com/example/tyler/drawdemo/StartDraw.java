package com.example.tyler.drawdemo;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import java.util.Random;

public class StartDraw extends Activity implements SensorEventListener {
    DrawView drawView;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    public float last_x;
    public float last_y;
    public float last_z;
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawView = new DrawView(this);
        //Initialize the player

        drawView.setBackgroundColor(Color.WHITE);
        //Get the bounds for the screen and send those to the player class
        getBounds();
        drawView.setBounds(SCREEN_WIDTH,SCREEN_HEIGHT);
        drawView.init();
        setContentView(drawView);
        //initialize the accelerometer
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void getBounds() {
        //Get the size of phone screen in width and height
        //Sets the screen height in terms of PIXEL values
        //Should probably just have the getBounds() method returns a point value for cleanliness sake
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH = size.x;
        SCREEN_HEIGHT = size.y;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //TODO
            //1. Calculate frame rate in a couple of different ways so that I can display them for debugging purposes

            //get the x, y, and z value of the accelerometer
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            //Need to check if the square is a player and if it is then use accelerometer values, if not use current AI values
            //TODO
            //Add different AI types, random, different based on player position, velocity etc.
            //Need to send the stuff that follows this statement into the DrawView class
            //Need to create a player and enemy class outside of the DrawView class
            long curTime = System.currentTimeMillis();
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;
            if(diffTime < 1000) {
                last_x = x;
                last_y = y;
                last_z = z;
                drawView.updateValues(x, y, diffTime);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }
}