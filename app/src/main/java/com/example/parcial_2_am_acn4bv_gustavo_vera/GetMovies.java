package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie implements Serializable {
    @JsonProperty("title")
    public String title;

    @JsonProperty("medium_cover_image")
    public String mediumCoverImage;

    @JsonProperty("year")
    public String year;

    @JsonProperty("rating")
    public String rating;

    @JsonProperty("genres")
    public List<String> genres;

    @JsonProperty("summary")
    public String summary;
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

public class GetMovies extends AsyncTask<String, Void, List<Movie>> {

    private AsyncTaskListener<List<Movie>> listener;

    public GetMovies(AsyncTaskListener<List<Movie>> listener) {
        this.listener = listener;
    }

    OkHttpClient client = new OkHttpClient();

    List<Movie> run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse apiResponse = objectMapper.readValue(response.body().string(), ApiResponse.class);
            return apiResponse.data.movies;
        }
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        String url = strings[0];
        try {
            return run(url);
        } catch (IOException e) {
            Log.e("Error", "Error en la conversión del JSON", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (listener != null) {
            listener.onTaskComplete(movies);
        }
    }

    public interface AsyncTaskListener<T> {
        void onTaskComplete(T result);
    }
}
