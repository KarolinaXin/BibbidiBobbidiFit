package com.example.bibbidibobbidifit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bibbidibobbidifit.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public int stepCount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final ImageView imageView = root.findViewById(R.id.little_red);

        final Button stepCounterBtn = root.findViewById(R.id.step_counter_btn);
        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                stepCount++;
                stepCounterBtn.setText("Step Count: " + stepCount);

                ConstraintLayout.LayoutParams layoutParams =
                        (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
//        layoutParams.leftMargin = x - xDelta;
                layoutParams.bottomMargin += 50;
//        layoutParams.rightMargin = 0;
//        layoutParams.bottomMargin = 0;
                imageView.setLayoutParams(layoutParams);
            }
        });

        return root;
    }
}