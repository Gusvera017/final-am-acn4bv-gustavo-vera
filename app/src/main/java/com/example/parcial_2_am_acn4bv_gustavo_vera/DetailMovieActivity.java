package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView detailTitleTextView = findViewById(R.id.textViewDetailMovieTitle);
        TextView detailYearTextView = findViewById(R.id.textViewDetailMovieYear);
        ImageView detailMediumCoverImageView = findViewById(R.id.imageViewDetailMovieCover);
        TextView detailGenreTextView = findViewById(R.id.textViewDetailMovieGenre);
        TextView detailSynopsisTextView = findViewById(R.id.textViewDetailMovieSynopsis);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String mediumCoverImage = intent.getStringExtra("mediumCoverImage");
            String year = intent.getStringExtra("year");
            String rating = intent.getStringExtra("rating");
            String[] genresArray = intent.getStringArrayExtra("genres");
            List<String> genresList = Arrays.asList(genresArray);
            String summary = intent.getStringExtra("summary");

            Log.i("DetailMovie", "Titulo: " + title);
            Log.i("DetailMovie", "Img: " + mediumCoverImage);
            Log.i("DetailMovie", "Año: " + year);
            Log.i("DetailMovie", "Rating: " + rating);
            Log.i("DetailMovie", "Genres: " + genresList);
            Log.i("DetailMovie", "Summary: " + summary);
            Log.i("DetailMovie", "-----------------------------------");

            detailTitleTextView.setText(title);
            detailYearTextView.setText(year);
            detailGenreTextView.setText("Genres: " + String.join(", ", genresList));
            detailSynopsisTextView.setText("Synopsis: " + summary);

            Picasso.get().load(mediumCoverImage).into(detailMediumCoverImageView);
        }
    }
}