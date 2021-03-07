package com.example.bibbidibobbidifit;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.WindowManager;

import com.example.bibbidibobbidifit.ui.home.HomeFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    public int stepCount = 0;

    //public Bundle bundle = new Bundle();
    public String userSteps = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            isCounterSensorPresent = false;
            System.out.println("User stepsstepCount " + stepCount);
            Bundle bundle = new Bundle();
            bundle.putString("stepCount", String.valueOf(stepCount));
            // set Fragmentclass Argument
            HomeFragment fragobj = new HomeFragment();
            fragobj.setArguments(bundle);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Button welcomeNextButton = (Button)findViewById(R.id.next_button);
        welcomeNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide welcome stuff
                Button welcomeNextButton = (Button)findViewById(R.id.next_button);
                ImageView welcomeBg = findViewById(R.id.red_bg);
                ImageView welcomeLilRed = findViewById(R.id.welcome_red);
                TextView welcomeText = findViewById(R.id.welcome_text);
                welcomeNextButton.setVisibility(View.GONE);
                welcomeBg.setVisibility(View.GONE);
                welcomeLilRed.setVisibility(View.GONE);
                welcomeText.setVisibility(View.GONE);

                // show get info screen
                Button getInfoButton = (Button)findViewById(R.id.get_info_next);
                ImageView getInfoBg = findViewById(R.id.get_info_bg);
                TextView getInfoTitle = findViewById(R.id.get_info_text);
                EditText nameInput = (EditText) findViewById(R.id.get_info_name_text);
                EditText ageInput = (EditText) findViewById(R.id.get_info_age_text);
                EditText countryInput = (EditText) findViewById(R.id.get_info_country_input);

                getInfoButton.setVisibility(View.VISIBLE);
                getInfoBg.setVisibility(View.VISIBLE);
                getInfoTitle.setVisibility(View.VISIBLE);
                nameInput.setVisibility(View.VISIBLE);
                ageInput.setVisibility(View.VISIBLE);
                countryInput.setVisibility(View.VISIBLE);
            }
        });

        Button getInfoButton = (Button)findViewById(R.id.get_info_next);
        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide get info stuff
                Button getInfoButton = (Button)findViewById(R.id.get_info_next);
                ImageView getInfoBg = findViewById(R.id.get_info_bg);
                TextView getInfoTitle = findViewById(R.id.get_info_text);
                EditText nameInput = (EditText) findViewById(R.id.get_info_name_text);
                EditText ageInput = (EditText) findViewById(R.id.get_info_age_text);
                EditText countryInput = (EditText) findViewById(R.id.get_info_country_input);

                getInfoButton.setVisibility(View.GONE);
                getInfoBg.setVisibility(View.GONE);
                getInfoTitle.setVisibility(View.GONE);
                nameInput.setVisibility(View.GONE);
                ageInput.setVisibility(View.GONE);
                countryInput.setVisibility(View.GONE);

                // show main screen
                showMainScreen();
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment homeFragment = manager.findFragmentById(R.id.nav_host_fragment);
        ft.hide(homeFragment);
        ft.commit();

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
                Button welcomeNextButton = (Button)findViewById(R.id.next_button);
                ImageView welcomeBg = findViewById(R.id.red_bg);
                ImageView welcomeLilRed = findViewById(R.id.welcome_red);
                TextView welcomeText = findViewById(R.id.welcome_text);
                welcomeNextButton.setVisibility(View.VISIBLE);
                welcomeBg.setVisibility(View.VISIBLE);
                welcomeLilRed.setVisibility(View.VISIBLE);
                welcomeText.setVisibility(View.VISIBLE);
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

    private void showMainScreen() {
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
}
