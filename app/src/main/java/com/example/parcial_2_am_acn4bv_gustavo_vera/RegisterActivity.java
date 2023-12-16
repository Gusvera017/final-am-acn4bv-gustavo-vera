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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public void register(String emailInputRegister, String passwordInputRegister, String firstNameInputRegister, String lastNameInputRegister) {
        Log.i("FirebaseReg", "email: " + emailInputRegister);
        Log.i("FirebaseReg", "password: " + passwordInputRegister);
        Log.i("FirebaseReg", "Nombre: " + firstNameInputRegister);
        Log.i("FirebaseReg", "Apellido: " + lastNameInputRegister);

        mAuth.createUserWithEmailAndPassword(emailInputRegister, passwordInputRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();

                            Map<String, Object> user = new HashMap<>();
                            user.put("nombre", firstNameInputRegister);
                            user.put("apellido", lastNameInputRegister);
                            user.put("uid", uid);
                            user.put("verificado", false);

                            db.collection("users")
                                    .document(uid)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.i("Firestore", "Usuario registrado en Firestore");
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("Firestore", "Error al registrar usuario en Firestore", e);
                                            Toast.makeText(RegisterActivity.this, "Falló el Registro.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Falló el Registro.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onBtnRegisterClick (View view) {
        EditText emailInputReg = findViewById(R.id.emailInputRegister);
        EditText passwordInputReg = findViewById(R.id.passwordInputRegister);
        EditText firstNameInputReg = findViewById(R.id.firstNameInputRegister);
        EditText lastNameInputReg = findViewById(R.id.lastNameInputRegister);

        String emailInputRegister = emailInputReg.getText().toString();
        String passwordInputRegister = passwordInputReg.getText().toString();
        String firstNameInputRegister = firstNameInputReg.getText().toString();
        String lastNameInputRegister = lastNameInputReg.getText().toString();

        this.register(emailInputRegister, passwordInputRegister, firstNameInputRegister, lastNameInputRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }
}