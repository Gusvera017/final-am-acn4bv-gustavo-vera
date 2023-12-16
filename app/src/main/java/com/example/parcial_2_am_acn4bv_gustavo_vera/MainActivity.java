package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    /* ORIGINAL CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        checkConnection();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        checkConnection();
    }

    public void checkConnectionOnClick(View view) {
        checkConnection();
    }

    public void checkConnection() {
        LinearLayout sinInternet = findViewById(R.id.container_msj_no_internet);
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            sinInternet.setVisibility(View.INVISIBLE);
            checkUserAuthentication();
        } else {
            sinInternet.setVisibility(View.VISIBLE);
        }
    }

    /* ORIGINAL CODE

    private void checkUserAuthentication() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    } */

    private void checkUserAuthentication() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            db.collection("users")
                    //Filtro de condición de búsqueda
                    .whereEqualTo("uid", uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for(QueryDocumentSnapshot document : task.getResult()) {
                                    String id = document.getId();
                                    Object data = document.getData();

                                    /*Actualización de la información

                                    db.collection("users")
                                            .document(id)
                                            .update("apellido", "Vera")
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task) {
                                                      if(task.isSuccessful()){
                                                           // Tal vez, mensaje de éxito!
                                                      }
                                                 }
                                            });*/

                                    Log.i("firestore", "id: " + id + " data: " + data.toString());
                                }
                            }
                        }
                    });

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
