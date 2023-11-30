package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements GetMovies.AsyncTaskListener<List<Movie>> {

    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cards horizaontales

        SearchView searchView = findViewById(R.id.searchViewMovies);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (movieAdapter != null) {
                    movieAdapter.filter(newText);
                }
                return true;
            }
        });

        //TextView textView = findViewById(R.id.text_peliculas);
        //textView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        logout(v);
        //    }
        //});

        obtenerInformacion();
        mAuth = FirebaseAuth.getInstance();
    }

    public void obtenerInformacion() {
        GetMovies getMovies = new GetMovies(this);
        getMovies.execute("https://yts.mx/api/v2/list_movies.json?limit=40");
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
        movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);
    }

    public void logout(View view) {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
