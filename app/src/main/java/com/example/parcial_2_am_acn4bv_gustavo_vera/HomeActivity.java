package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements GetMovies.AsyncTaskListener<List<Movie>> {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        obtenerInformacion();
        mAuth = FirebaseAuth.getInstance();
    }

    public void obtenerInformacion() {
        GetMovies getMovies = new GetMovies(this);
        getMovies.execute("https://yts.mx/api/v2/list_movies.json");
    }

    @Override
    public void onTaskComplete(List<Movie> movies) {
        for (Movie movie : movies) {
            Log.i("peliculasHome", "Titulo: " + movie.title);
            Log.i("peliculasHome", "Img: " + movie.mediumCoverImage);
            Log.i("peliculasHome", "Año: " + movie.year);
            Log.i("peliculasHome", "Rating: " + movie.rating);
            Log.i("peliculasHome", "-----------------------------------");
        }

    }

    public void logout(View view) {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
