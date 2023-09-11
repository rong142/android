package com.example.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.mycafe.databinding.ActivityShopBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class shopping extends AppCompatActivity {

    ActivityShopBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        varInit();
    }


    private void varInit() {
        binding.q1.addTextChangedListener(editTextWatcher);
        binding.q2.addTextChangedListener(editTextWatcher);
        binding.q3.addTextChangedListener(editTextWatcher);
        binding.ok.setOnClickListener(btnOKClick);
    }

    private void calcAmount() {
        int qty1 = 0, qty2 = 0, qty3 = 0;
        int discount, amount;
        try {
            qty1 = Integer.parseInt(binding.q1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            qty2 = Integer.parseInt(binding.q2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            qty3 = Integer.parseInt(binding.q3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        amount = qty1 * 100 + qty2 * 50 + qty3 * 150;
        discount = amount - (int) (amount * 0.8);
        amount -= discount;
        binding.nt1.setText(String.valueOf(discount));
        binding.nt2.setText(String.valueOf(amount));
    }

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calcAmount();
        }
    };

    private final View.OnClickListener btnOKClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int saleAmount = 0;
            int qty1 = 0, qty2 = 0, qty3 = 0;
            String s1 = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);

            try {
                qty1 = Integer.parseInt(binding.q1.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                qty2 = Integer.parseInt(binding.q2.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                qty3 = Integer.parseInt(binding.q3.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                saleAmount = Integer.parseInt(binding.nt2.getText().toString());
            } catch (Exception e) {
            }

            if (saleAmount > CommonData.MemberValue) {
                Toast.makeText(shopping.this, "餘額不足，請先儲值", Toast.LENGTH_SHORT).show();
                return;
            }

            s1 = sdf.format(new Date());
            if (qty1 > 0)
                s1 += (s1.equals("") ? "" : "\n") + "拿鐵" + qty1 + "份";

            if (qty2 > 0)
                s1 += (s1.equals("") ? "" : "\n") + "餅乾" + qty2 + "份";

            if (qty3 > 0)
                s1 += (s1.equals("") ? "" : "\n") + "蛋糕" + qty3 + "份";

            s1 += (s1.equals("") ? "" : "\n") + "總計:" + saleAmount + "元," + (binding.radioButton1.isChecked() ? "自取" : "外送");
            s1 += "\n";
            CommonData.MemberValue -= saleAmount;
            CommonData.MemberSellRecord.add(0, s1);//倒著
            Toast.makeText(shopping.this, "交易完成", Toast.LENGTH_SHORT).show();
            binding.q1.setText("");
            binding.q2.setText("");
            binding.q3.setText("");
        }
    };
}