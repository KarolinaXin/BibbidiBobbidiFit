package com.example.bibbidibobbidifit.ui.home;

import android.content.Intent;
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
import com.example.bibbidibobbidifit.ui.ChallengeNoActionDialogFragment.ChallengeNoActionDialogFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static int stepCount;
    public static int currentQuestStepCount;
    public static enum Quest {
        NONE("", 0),
        TRIP_TO_GRANDMAS("Trip to Grandma's", 8);

        private final String name;
        private final int target;

        private Quest(String name, int target) {
            this.name = name;
            this.target = target;
        }

        public String getName() {
            return name;
        }

        public int getTarget() {
            return target;
        }
    }
    public static Quest currentQuest;
    public static TextView currentQuestTextView;
    public static TextView stepsLeftTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final FragmentManager fragmentManager = this.getFragmentManager();

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView stepCountTextView = root.findViewById(R.id.step_count_text_view);

        HomeFragment.currentQuestTextView = root.findViewById(R.id.current_quest_text_view);
        HomeFragment.currentQuestTextView.setVisibility(View.GONE);
        HomeFragment.stepsLeftTextView = root.findViewById(R.id.current_quest_step_left_text_view);
        HomeFragment.stepsLeftTextView.setVisibility(View.GONE);

        final ImageView imageView = root.findViewById(R.id.little_red);

        final Button stepCounterBtn = root.findViewById(R.id.step_counter_btn);
        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                HomeFragment.stepCount++;
                if (HomeFragment.currentQuest != Quest.NONE) {
                    HomeFragment.currentQuestStepCount--;
                }
                stepCounterBtn.setText("Step Count: " + HomeFragment.stepCount);
                stepCountTextView.setText("Steps walked today: " + HomeFragment.stepCount);
                stepsLeftTextView.setText("Steps left in quest: " + HomeFragment.currentQuestStepCount);
                ConstraintLayout.LayoutParams layoutParams =
                        (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.bottomMargin += 50;
                imageView.setLayoutParams(layoutParams);

                if (HomeFragment.currentQuestStepCount == HomeFragment.currentQuest.getTarget()) {
                    final String congratsTitle = "Congratulations!";
                    final String congratsText = "You have completed the quest: " + HomeFragment.currentQuest.getName();
                    final ChallengeNoActionDialogFragment challengeCompleteFragment = new ChallengeNoActionDialogFragment(congratsTitle, congratsText);
                    challengeCompleteFragment.show(fragmentManager, "ChallengeCompleteFragment");
                    HomeFragment.currentQuest = Quest.NONE;
                    HomeFragment.currentQuestTextView.setVisibility(View.GONE);
                    HomeFragment.stepsLeftTextView.setVisibility(View.GONE);
                }
            }
        });

        final ChallengeDialogFragment challengeDialogFragment = new ChallengeDialogFragment();
        final Button challengeBtn = root.findViewById(R.id.challenge_btn);
        challengeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                challengeDialogFragment.show(fragmentManager, "ChallengeDialogFragment");
            }
        });

        return root;
    }
}