package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    /*
    * Datos de prueba:
    * Email: gustavo.vera@davinci.edu.ar
    * Pass: prueba1234
    * Email: probando@probando.com
    * Pass: prueba123456
    *
    * AGREGAR UNA AUTENTICACIÓN
    *
    *
    * */

    private FirebaseAuth mAuth;

    public void login(String emailInputLogin, String passwordInputLogin) {
        Log.i("Firebase", "email: " + emailInputLogin);
        Log.i("Firebase", "password: " + passwordInputLogin);

        mAuth.signInWithEmailAndPassword(emailInputLogin, passwordInputLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Falló la Autenticación.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void register(String emailInputLogin, String passwordInputLogin) {
        Log.i("Firebase", "email: " + emailInputLogin);
        Log.i("Firebase", "password: " + passwordInputLogin);

        mAuth.createUserWithEmailAndPassword(emailInputLogin, passwordInputLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Falló el Registro.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onButtonLoginClick (View view) {
        EditText emailInput = findViewById(R.id.emailInputLogin);
        EditText passwordInput = findViewById(R.id.passwordInputLogin);

        String emailInputLogin = emailInput.getText().toString();
        String passwordInputLogin = passwordInput.getText().toString();

        this.login(emailInputLogin, passwordInputLogin);
    }

    public void onButtonRegisterClick (View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }
}