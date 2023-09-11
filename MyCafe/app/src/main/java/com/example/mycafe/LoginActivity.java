package com.example.mycafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mycafe.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginBinding binding;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);

        // Button 初始化
        Button button = findViewById(R.id.btn_old);
        Button button1 = findViewById(R.id.btn_new);
        button.setOnClickListener(example);
        button1.setOnClickListener(example);

    }

        private View.OnClickListener example = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_old: {
                        Intent login = new Intent(LoginActivity.this, LoginSignup.class);
                        startActivity(login);
                        //showOldDialog(view);
                        break;
                    }
                    case R.id.btn_new:{
                        showNewDialog(view);
                        break;
                    }
                }
            }
        };


    public void showNewDialog ( View view ){
        NewLoginDialogFragment newLoginDialogFragment = new NewLoginDialogFragment();
        newLoginDialogFragment.show(getSupportFragmentManager() ,"建立新帳號");
    }
    public void showOldDialog ( View view ){
        OldLoginDialogFragment oldLoginDialogFragment = new OldLoginDialogFragment();
        oldLoginDialogFragment.show(getSupportFragmentManager() ,"輸入帳號密碼");
    }

    }


