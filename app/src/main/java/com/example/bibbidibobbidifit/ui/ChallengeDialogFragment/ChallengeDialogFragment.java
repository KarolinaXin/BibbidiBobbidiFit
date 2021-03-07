package com.example.bibbidibobbidifit.ui.ChallengeDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.bibbidibobbidifit.R;
import com.example.bibbidibobbidifit.ui.home.HomeFragment;

public class ChallengeDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO(ellen): randomly select quest name from given list
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.quest_title_prefix) + getResources().getString(R.string.quest_title_trip_to_grandmas));
        builder.setMessage(R.string.quest_body_trip_to_grandmas).setPositiveButton(R.string.quest_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HomeFragment.currentQuest = HomeFragment.Quest.TRIP_TO_GRANDMAS;
                HomeFragment.currentQuestTextView.setVisibility(View.VISIBLE);
                HomeFragment.currentQuestTextView.setText(getResources().getString(R.string.current_quest_prefix) + HomeFragment.currentQuest.getName());
                HomeFragment.currentQuestStepCount = HomeFragment.currentQuest.getTarget();
                HomeFragment.stepsLeftTextView.setText("Steps left in quest: " + HomeFragment.currentQuestStepCount);
                HomeFragment.stepsLeftTextView.setVisibility(View.VISIBLE);
            }
        }).setNegativeButton(R.string.quest_decline, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HomeFragment.currentQuest = HomeFragment.Quest.NONE;
                HomeFragment.currentQuestTextView.setVisibility(View.GONE);
            }
        });
        // TODO(ellen): display some sort of UI confirmation that an action was taken
        // Create the AlertDialog object and return it
        return builder.create();
    }
}