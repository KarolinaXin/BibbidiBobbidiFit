package com.example.bibbidibobbidifit.ui.fitnessgoal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FitnessGoalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FitnessGoalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}