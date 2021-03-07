package com.example.bibbidibobbidifit.ui.ChallengeDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.bibbidibobbidifit.R;

public class ChallengeDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO(ellen): randomly select quest name from given list
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.quest_title_prefix) + getResources().getString(R.string.quest_title_trip_to_grandmas));
        builder.setMessage(R.string.quest_body_trip_to_grandmas).setPositiveButton(R.string.quest_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                builder.setTitle("");
                builder.setMessage("Challenge Accepted!");
            }
        }).setNegativeButton(R.string.quest_decline, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                builder.setTitle("");
                builder.setMessage("Challenge Declined.");
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}