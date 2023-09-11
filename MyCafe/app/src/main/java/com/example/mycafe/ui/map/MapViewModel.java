package com.example.mycafe.ui.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("地址：\n高雄市小港區明聖街135巷10弄");
    }

    public LiveData<String> getText() {
        return mText;
    }
}