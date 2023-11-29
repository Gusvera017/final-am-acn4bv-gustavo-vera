package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        // Configura las vistas de la tarjeta con los datos de la película
        holder.textViewTitle.setText(movie.title);
        holder.textViewYear.setText(movie.year);
        holder.textViewRating.setText(movie.rating);

        // Puedes configurar otras vistas aquí (imagen, año, rating, etc.)
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewYear;
        TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}

