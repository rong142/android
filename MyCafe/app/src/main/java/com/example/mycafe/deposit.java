package com.example.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycafe.databinding.ActivityDepositBinding;
import com.google.android.gms.common.internal.service.Common;

public class deposit extends AppCompatActivity {

    private boolean lastIsOperator; //新增
    private String lastOperators = "";//新增
    private double firstNumber = 0D;//新增
    private double secondNumber = 0D;//新增

    ActivityDepositBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDepositBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        varInit();
    }


    private void varInit(){

        binding.textMoney1.setText(String.valueOf(CommonData.MemberValue));

        binding.button1.setOnClickListener(buttonClick);
        binding.button2.setOnClickListener(buttonClick);
        binding.button3.setOnClickListener(buttonClick);
        binding.button4.setOnClickListener(buttonClick);
        binding.button5.setOnClickListener(buttonClick);
        binding.button6.setOnClickListener(buttonClick);
        binding.button7.setOnClickListener(buttonClick);
        binding.button8.setOnClickListener(buttonClick);
        binding.button9.setOnClickListener(buttonClick);
        binding.button0.setOnClickListener(buttonClick);
        binding.button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                binding.textMoney2.setText("0");
            }
        });
        binding.button11.setOnClickListener(buttonConfirm);

    }




    private final View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override


        public void onClick(View v) {

            String strBtn = ((Button)v).getText().toString();
            String strMoney = binding.textMoney2.getText().toString();

            if (strMoney.equals("0"))
                strMoney=strBtn;
            else
                strMoney= strMoney + strBtn;
            binding.textMoney2.setText(strMoney);



        }
    };


    private final View.OnClickListener buttonConfirm = v-> {
        int money =0;

        try {
            money = Integer.parseInt(binding.textMoney2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (money==0)
            return;

        binding.imageView7.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rotate));

        CommonData.MemberValue += money;
        binding.textMoney1.setText(String.valueOf(CommonData.MemberValue));
        binding.textMoney2.setText("0");
        Toast.makeText(deposit.this, "儲值完成!", Toast.LENGTH_SHORT).show();
    };


}