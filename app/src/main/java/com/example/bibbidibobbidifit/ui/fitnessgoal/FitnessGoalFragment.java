package com.example.bibbidibobbidifit.ui.fitnessgoal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bibbidibobbidifit.R;

public class FitnessGoalFragment extends Fragment {

    private FitnessGoalViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(FitnessGoalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fitness_goal, container, false);

        return root;
    }
}