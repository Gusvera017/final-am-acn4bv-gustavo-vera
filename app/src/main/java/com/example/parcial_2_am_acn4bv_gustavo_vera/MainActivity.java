package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;

    public void checkConnection() {
        LinearLayout sinInternet = findViewById(R.id.container_msj_no_internet);
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            sinInternet.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            sinInternet.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection();

        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
            //Log.i("Firebase", "Usuario Autenticado");
            ////Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            ////startActivity(intent);
//        } else {
//            Log.i("Firebase", "Usuario NO Autenticado, redirigir");
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }
//    }

    /*public void logout(View view) {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }*/
}