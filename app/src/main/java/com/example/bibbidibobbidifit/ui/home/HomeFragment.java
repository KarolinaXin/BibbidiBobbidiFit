package com.example.bibbidibobbidifit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.bibbidibobbidifit.R;
import com.example.bibbidibobbidifit.ui.ChallengeDialogFragment.ChallengeDialogFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public int stepCount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final FragmentManager fragmentManager = this.getFragmentManager();

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView stepCountTextView = root.findViewById(R.id.step_count_text_view);

        final ImageView imageView = root.findViewById(R.id.little_red);

        final Button stepCounterBtn = root.findViewById(R.id.step_counter_btn);
        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                stepCount++;
                stepCounterBtn.setText("Step Count: " + stepCount);
                stepCountTextView.setText("Steps walked today: " + stepCount);
                ConstraintLayout.LayoutParams layoutParams =
                        (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.bottomMargin += 50;
                imageView.setLayoutParams(layoutParams);
            }
        }) ;

        final Button challengeBtn = root.findViewById(R.id.challenge_btn);
        challengeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ChallengeDialogFragment tmp = new ChallengeDialogFragment();
                tmp.show(fragmentManager, "NoticeDialogFragment");
            }
        });

        return root;
    }
}