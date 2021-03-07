package com.example.bibbidibobbidifit.ui.ChallengeNoActionDialogFragment;




import android.app.AlertDialog;

import android.app.Dialog;

import android.os.Bundle;




import androidx.fragment.app.DialogFragment;




public class ChallengeNoActionDialogFragment extends DialogFragment {

    private String title;

    private String text;




    public ChallengeNoActionDialogFragment(String title, String text) {

        this.title = title;

        this.text = text;

    }




    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title);

        builder.setMessage(text);

        // Create the AlertDialog object and return it

        return builder.create();

    }

}
