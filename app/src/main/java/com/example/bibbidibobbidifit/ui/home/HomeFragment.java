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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bibbidibobbidifit.MainActivity;
import com.example.bibbidibobbidifit.R;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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
        final TextView textViewSteps = (TextView) root.findViewById(R.id.textViewSteps);
        final TextView welcomeSpeech = (TextView) root.findViewById(R.id.textView4);
        final ImageView speechImage = root.findViewById(R.id.speech);

        final int initialSteps = (Integer.parseInt(((MainActivity)getActivity()).getUserSteps()));
        System.out.println("Initial Steps " + initialSteps);

        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             // Code here executes on main thread after user presses button
            System.out.println("Yo momma stepcount " + ((MainActivity)getActivity()).getUserSteps());
            int netSteps = (Integer.parseInt(((MainActivity)getActivity()).getUserSteps()) - initialSteps + 100);

            textViewSteps.setText("Steps adventured today: " + netSteps);


            welcomeSpeech.setVisibility(View.GONE);//makes it disappear
            speechImage.setVisibility(View.GONE);//makes it disappear

            ConstraintLayout.LayoutParams layoutParams =
                    (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.bottomMargin = (netSteps);

            if (netSteps > 131) {
                // TODO: get ready for a challenge pop up here. Something about "Oh no! Little Red has stopped to gather flowers for her Grandma. Adventure 500 steps to pick the very best for her Granny"

            }
            imageView.setLayoutParams(layoutParams);
            }
        });

        return root;
    }

}