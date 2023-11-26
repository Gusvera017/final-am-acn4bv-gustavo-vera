package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public void login(String email, String password) {
        Log.i("Firebase", "email: " + email);
        Log.i("Firebase", "password: " + password);
    }

    public void onButtonLoginClick (View view) {
        EditText emailInput = findViewById(R.id.emailInputLogin);
        EditText passwordInput = findViewById(R.id.passwordInputLogin);

        String emailInputLogin = emailInput.getText().toString();
        String passwordInputLogin = passwordInput.getText().toString();

        this.login(emailInputLogin, passwordInputLogin);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}