package com.example.mycafe.ui.shop;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.mycafe.MainActivity;
import com.example.mycafe.R;
import com.example.mycafe.shopping;


public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(" ");
    }



    public LiveData<String> getText() {
        return mText;
    }




}
