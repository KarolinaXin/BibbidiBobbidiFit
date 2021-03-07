package com.example.bibbidibobbidifit;

import android.os.Bundle;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
