package com.example.bibbidibobbidifit.ui.ChallengeNoActionDialogFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChallengeNoActionDialogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChallengeNoActionDialogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is challenge no-action dialog fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
