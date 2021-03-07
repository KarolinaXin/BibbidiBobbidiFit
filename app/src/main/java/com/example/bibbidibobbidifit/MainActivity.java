package com.example.bibbidibobbidifit;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
<<<<<<< HEAD
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bibbidibobbidifit.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
=======
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bibbidibobbidifit.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> 0175c15... Added steps to little red and some UI upgrades
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

<<<<<<< HEAD

public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textViewStepCounter, textViewStepDetector;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    public int stepCount = 0;

    //public Bundle bundle = new Bundle();
    public String userSteps = "0";
    public String stepCount2 = "";
>>>>>>> 0175c15... Added steps to little red and some UI upgrades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
=======

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       // textViewStepDetector = findViewById(R.id.textViewStepDetector);




        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            isCounterSensorPresent = false;
//            HomeFragment fragment = new HomeFragment(); //your fragment
//            String s = String.valueOf(stepCount);
//            fragment.setUserStepCount(s);
            System.out.println("User stepsstepCount " + stepCount);
            Bundle bundle = new Bundle();
            bundle.putString("stepCount", String.valueOf(stepCount));
            // set Fragmentclass Argument
            HomeFragment fragobj = new HomeFragment();
            fragobj.setArguments(bundle);
        }

>>>>>>> 0175c15... Added steps to little red and some UI upgrades
        BottomNavigationView navView = findViewById(R.id.nav_view);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment homeFragment = manager.findFragmentById(R.id.nav_host_fragment);
        ft.hide(homeFragment);
        ft.commit();

        navView.setVisibility(View.GONE);
        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation fadeOut = new AlphaAnimation(1, 0);
                    //fadeOut.setInterpolator(new AccelerateInterpolator());
                    //fadeOut.setStartOffset(1000);
                    fadeOut.setDuration(400);

                    ImageView mulanImage = findViewById(R.id.mulan_image);
                    mulanImage.setAnimation(fadeOut);
                    mulanImage.animate();
                    mulanImage.setVisibility(View.GONE);

                    TextView titleText = findViewById(R.id.app_name_text);
                    titleText.setAnimation(fadeOut);
                    titleText.animate();
                    titleText.setVisibility(View.GONE);
                }
            }, 3000
        );

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BottomNavigationView navView = findViewById(R.id.nav_view);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                Fragment homeFragment = manager.findFragmentById(R.id.nav_host_fragment);
                ft = manager.beginTransaction();
                ft.show(homeFragment);
                ft.commit();

                getSupportActionBar().show();
                navView.setVisibility(View.VISIBLE);
            }
        }, 3300);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.unregisterListener(this, mStepCounter);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == mStepCounter) {
            stepCount = (int) sensorEvent.values[0];

            userSteps = String.valueOf(stepCount);
            System.out.println("User steps " + userSteps);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public String getUserSteps() {
        return userSteps;
    }

}
