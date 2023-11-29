package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie {
    @JsonProperty("title")
    public String title;

    @JsonProperty("medium_cover_image")
    public String mediumCoverImage;

    @JsonProperty("year")
    public String year;

    @JsonProperty("rating")
    public String rating;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class MovieData {
    @JsonProperty("movies")
    public List<Movie> movies;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ApiResponse {
    @JsonProperty("data")
    public MovieData data;
}

public class GetMovies extends AsyncTask<String, Integer, String> {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String response = "";
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Movie> movies = apiResponse.data.movies;

            for(int i = 0; i < movies.size(); i++) {
                Log.i("peliculas", "Titulo: "+ movies.get(i).title);
                Log.i("peliculas", "Img: " + movies.get(i).mediumCoverImage);
                Log.i("peliculas", "Año: " + movies.get(i).year);
                Log.i("peliculas", "Rating: " + movies.get(i).rating);
                Log.i("peliculas", "-----------------------------------");
            }

        } catch (IOException e) {
            Log.e("Error", "Error en la conversión del JSON", e);
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("probando_url", s);
    }
}
