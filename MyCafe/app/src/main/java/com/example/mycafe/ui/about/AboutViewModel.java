package com.example.mycafe.ui.about;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("　　位於小港國際機場跑道旁，對於航空迷來說，是個觀看飛機起降最好的地點，有寬廣的視野，觀看飛機的同時，可以點個咖啡、飲料及餐點，就可以很放鬆的待一整天。\n\n\n\n地址：高雄市小港區明聖街135巷10弄\n\n電話：0978-886-356\n\n營業時間：08：00 - 23：00");
    }

    public LiveData<String> getText() {
        return mText;
    }

}