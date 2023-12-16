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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public void register(String emailInputRegister, String passwordInputRegister) {
        Log.i("FirebaseReg", "email: " + emailInputRegister);
        Log.i("FirebaseReg", "password: " + passwordInputRegister);

        mAuth.createUserWithEmailAndPassword(emailInputRegister, passwordInputRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Falló el Registro.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onBtnRegisterClick (View view) {
        EditText emailInputReg = findViewById(R.id.emailInputRegister);
        EditText passwordInputReg = findViewById(R.id.passwordInputRegister);

        String emailInputRegister = emailInputReg.getText().toString();
        String passwordInputRegister = passwordInputReg.getText().toString();

        this.register(emailInputRegister, passwordInputRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
    }
}