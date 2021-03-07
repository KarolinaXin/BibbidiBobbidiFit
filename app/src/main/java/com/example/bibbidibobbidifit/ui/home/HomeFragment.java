package com.example.bibbidibobbidifit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bibbidibobbidifit.MainActivity;
import com.example.bibbidibobbidifit.R;
import com.example.bibbidibobbidifit.ui.ChallengeDialogFragment.ChallengeDialogFragment;
import com.example.bibbidibobbidifit.ui.ChallengeNoActionDialogFragment.ChallengeNoActionDialogFragment;
import com.example.bibbidibobbidifit.ui.dashboard.DashboardViewModel;


import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public boolean doneQuest = false;
    public static int amountStepsAtQuest;

    //Ellen's
    public static int stepCount;
    public static int currentQuestStepCount = 8;
    public static enum Quest {
        NONE("", -1),
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

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        HomeFragment.currentQuest = Quest.NONE;
        HomeFragment.currentQuestTextView = root.findViewById(R.id.current_quest_text_view);
        HomeFragment.currentQuestTextView.setVisibility(View.GONE);
        HomeFragment.stepsLeftTextView = root.findViewById(R.id.current_quest_step_left_text_view);
        HomeFragment.stepsLeftTextView.setVisibility(View.GONE);


        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final ImageView imageView = root.findViewById(R.id.little_red);
        final Button stepCounterBtn = root.findViewById(R.id.step_counter_btn);
        final TextView textViewSteps = (TextView) root.findViewById(R.id.textViewSteps);
        final TextView welcomeSpeech = (TextView) root.findViewById(R.id.textView4);
        final ImageView speechImage = root.findViewById(R.id.speech);

        final int initialSteps = (Integer.parseInt(((MainActivity)getActivity()).getUserSteps()));
        System.out.println("Initial Steps " + initialSteps);




        // THIS CODE IS FOR TESTING, AND TREATS EACH CLICK OF THE "TAKE STEP" BUTTON AS A REAL STEP
        HomeFragment.stepCount++;
        stepsLeftTextView.setText("Steps left in quest: " + HomeFragment.currentQuestStepCount);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.bottomMargin += 50;

        final ChallengeDialogFragment challengeDialogFragment = new ChallengeDialogFragment();

        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             // Code here executes on main thread after user presses button
            System.out.println("Yo momma stepcount " + ((MainActivity)getActivity()).getUserSteps());
            int netSteps = ((Integer.parseInt(((MainActivity)getActivity()).getUserSteps()) - initialSteps) + 100);



            textViewSteps.setText("Steps adventured today: " + netSteps);
            netSteps = (netSteps - 100)*10 + 100;

            if (HomeFragment.currentQuest != Quest.NONE && HomeFragment.currentQuestStepCount > 0) {
                HomeFragment.currentQuestStepCount--;
                stepsLeftTextView.setText("Steps left in quest: " + HomeFragment.currentQuestStepCount);
                System.out.println("HomeFragment.amountStepsAtQuest " + HomeFragment.amountStepsAtQuest);
                System.out.println("netsteps " + netSteps);
               // HomeFragment.currentQuestStepCount = netSteps - HomeFragment.amountStepsAtQuest;
                System.out.println("HomeFragment.currentQuestStepCount "+ HomeFragment.currentQuestStepCount);
            }


                if (HomeFragment.currentQuestStepCount <= 0) {
                    final String congratsTitle = "Congratulations!";
                    final String congratsText = "You have completed the quest: " + HomeFragment.currentQuest.getName();
                    final ChallengeNoActionDialogFragment challengeCompleteFragment = new ChallengeNoActionDialogFragment(congratsTitle, congratsText);
                    challengeCompleteFragment.show(fragmentManager, "ChallengeCompleteFragment");
                    HomeFragment.currentQuest = Quest.NONE;
                    HomeFragment.currentQuestTextView.setVisibility(View.GONE);
                    HomeFragment.stepsLeftTextView.setVisibility(View.GONE);
                }


            welcomeSpeech.setVisibility(View.GONE);//makes it disappear
            speechImage.setVisibility(View.GONE);//makes it disappear





                ConstraintLayout.LayoutParams layoutParams =
                    (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.bottomMargin = (netSteps);
            System.out.println("NetSteps before quest " + netSteps + "truth " + doneQuest);
            if (netSteps > 301 && doneQuest == false) {
                amountStepsAtQuest = netSteps;
                doneQuest = true;
                challengeDialogFragment.show(fragmentManager, "ChallengeDialogFragment");
                // TODO: get ready for a challenge pop up here. Something about "Oh no! Little Red has stopped to gather flowers for her Grandma. Adventure 500 steps to pick the very best for her Granny"
            }
            imageView.setLayoutParams(layoutParams);
            }
        });

        return root;
    }

}