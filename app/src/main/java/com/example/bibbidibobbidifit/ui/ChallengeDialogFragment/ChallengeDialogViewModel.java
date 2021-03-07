package com.example.bibbidibobbidifit.ui.ChallengeDialogFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChallengeDialogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChallengeDialogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is challenge dialog fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}