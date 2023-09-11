package com.example.mycafe.ui.mail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MailViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("電話：0978 886 356\n\nmail：v073099@gmail.com" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}