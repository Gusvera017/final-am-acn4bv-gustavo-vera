package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements GetMovies.AsyncTaskListener<List<Movie>>, MovieAdapter.OnMovieClickListener {

    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cards horizontales

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

        Button buttonOptions = findViewById(R.id.buttonOptions);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsMenu(v);
            }
        });

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
            Log.i("peliculasHome", "Genres: " + movie.genres);
            Log.i("peliculasHome", "Summary: " + movie.summary);
            Log.i("peliculasHome", "-----------------------------------");
        }
        movieAdapter = new MovieAdapter(movies, this);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieClick(int position) {
        //Intent intent = new Intent(getApplicationContext(), DetailMovieActivity.class);
        // startActivity(intent);

        if (movieAdapter != null) {
            Movie selectedMovie = movieAdapter.getMovie(position);

            Intent intent = new Intent(getApplicationContext(), DetailMovieActivity.class);
            intent.putExtra("title", selectedMovie.title);
            intent.putExtra("mediumCoverImage", selectedMovie.mediumCoverImage);
            intent.putExtra("year", selectedMovie.year);
            intent.putExtra("rating", selectedMovie.rating);
            String[] genresArray = selectedMovie.genres.toArray(new String[0]);
            intent.putExtra("genres", genresArray);
            intent.putExtra("summary", selectedMovie.summary);

            startActivity(intent);
        }
    }

    private void showOptionsMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.options_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_item_logout) {
                    logout();
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    public void logout() {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
