package com.example.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginSignup extends AppCompatActivity {

    TextInputLayout email,password;
    Button login,signup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        email = findViewById( R.id.email );
        password = findViewById( R.id.password );
        login = findViewById( R.id.btn_login );
        signup = findViewById( R.id.btn_signup );

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getEditText().getText().toString();
                String p = password.getEditText().getText().toString();

                auth.signInWithEmailAndPassword( e,p ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity( new Intent(LoginSignup.this,MainActivity.class) );
                            Toast.makeText( LoginSignup.this, "Login !", Toast.LENGTH_SHORT ).show();
                            finish();
                        }else {
                            Toast.makeText( LoginSignup.this, "Login Fail!", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );

            }
        } );

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getEditText().getText().toString();
                String p = password.getEditText().getText().toString();


                auth.createUserWithEmailAndPassword( e,p ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText( LoginSignup.this, "Account Creat Successful", Toast.LENGTH_SHORT ).show();
                        }else {
                            Toast.makeText( LoginSignup.this, "SignUp Fail!", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );


            }
        } );

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (auth != null){
            startActivity( new Intent(LoginSignup.this,MainActivity.class) );
            finish();
        }
    }


}