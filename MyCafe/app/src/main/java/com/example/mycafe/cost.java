package com.example.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mycafe.databinding.ActivityCostBinding;
import com.google.android.gms.common.internal.service.Common;

public class cost extends AppCompatActivity {

    ActivityCostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityCostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        varInit();
    }

    private void varInit(){
        int i;
        String s1;

        s1="";
        for (i=0; i< CommonData.MemberSellRecord.size();i++) {
            s1 +=CommonData.MemberSellRecord.get(i) + "\n";
        }

        binding.record.setText(s1);
    }
}