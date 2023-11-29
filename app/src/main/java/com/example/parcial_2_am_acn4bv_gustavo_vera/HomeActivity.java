package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        obtenerInformacion();
        mAuth = FirebaseAuth.getInstance();
    }

    public void obtenerInformacion() {
        GetMovies getMovies = new GetMovies();
        getMovies.execute("https://yts.mx/api/v2/list_movies.json");
    }

    public void logout(View view) {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}