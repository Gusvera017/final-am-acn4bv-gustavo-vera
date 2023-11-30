package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private List<Movie> originalMovies;
    private OnMovieClickListener onMovieClickListener;

    public MovieAdapter(List<Movie> movies, OnMovieClickListener onMovieClickListener) {
        this.movies = movies;
        this.originalMovies = new ArrayList<>(movies);
        this.onMovieClickListener = onMovieClickListener;
    }

    public interface OnMovieClickListener {
        void onMovieClick(int position);
    }

    private String getShortenedTitle(String fullTitle) {
        final int MAX_TITLE_LENGTH = 20;

        if (fullTitle.length() > MAX_TITLE_LENGTH) {
            return fullTitle.substring(0, MAX_TITLE_LENGTH - 3) + " ...";
        } else {
            return fullTitle;
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        Picasso.get()
                .load(movie.mediumCoverImage)
                .into(holder.imageViewCover);

        holder.textViewTitle.setText(getShortenedTitle(movie.title));
        holder.textViewYear.setText(movie.year);
        holder.textViewRating.setText(movie.rating + "/10");
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void filter(String query) {
        movies.clear();
        if (query.isEmpty()) {
            movies.addAll(originalMovies);
        } else {
            query = query.toLowerCase();
            for (Movie movie : originalMovies) {
                if (movie.title.toLowerCase().contains(query)) {
                    movies.add(movie);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewCover;
        TextView textViewTitle;
        TextView textViewYear;
        TextView textViewRating;
        OnMovieClickListener onMovieClickListener;

        public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.imageViewCover);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            this.onMovieClickListener = onMovieClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieClickListener.onMovieClick(getAdapterPosition());
        }
    }
}

