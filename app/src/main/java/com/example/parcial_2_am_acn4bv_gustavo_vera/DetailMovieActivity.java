package com.example.parcial_2_am_acn4bv_gustavo_vera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String mediumCoverImage = intent.getStringExtra("mediumCoverImage");
            String year = intent.getStringExtra("year");
            String rating = intent.getStringExtra("rating");

            Log.i("DetailMovie", "Titulo: " + title);
            Log.i("DetailMovie", "Img: " + mediumCoverImage);
            Log.i("DetailMovie", "Año: " + year);
            Log.i("DetailMovie", "Rating: " + rating);
            Log.i("DetailMovie", "-----------------------------------");
        }
    }
}