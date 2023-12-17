package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserPageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView textViewNombreApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        textViewNombreApellido = findViewById(R.id.textViewNombreApellido);

        obtenerInformacionUsuario();
    }

    private void obtenerInformacionUsuario() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            Log.i("UserPageActivity", "UID del usuario actual: " + uid);
            db.collection("users")
                    .document(uid)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String nombre = document.getString("nombre");
                                String apellido = document.getString("apellido");

                                mostrarDatosUsuario(nombre, apellido);
                                Log.i("UserPageActivity", "nombre: " + nombre + " apellido: " + apellido);
                            } else {
                                Log.i("UserPageActivity", "El documento no existe");
                            }
                        } else {
                            Log.i("UserPageActivity", "Error al obtener datos", task.getException());
                        }
                    });
        }
    }

    private void mostrarDatosUsuario(String nombre, String apellido) {
        String nombreCompleto = nombre + " " + apellido;
        textViewNombreApellido.setText(nombreCompleto);
    }
}
