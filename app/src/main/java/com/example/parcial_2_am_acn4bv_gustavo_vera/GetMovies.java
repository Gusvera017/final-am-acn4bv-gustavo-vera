package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
            JSONObject moviesInfo = new JSONObject(response);
            JSONObject data = moviesInfo.getJSONObject("data");
            JSONArray movies = (JSONArray) data.get("movies");
            JSONObject moviesInfo0 = (JSONObject) movies.get(0);
            String nameMovie0 = (String) moviesInfo0.get("title");
            response = nameMovie0;
        } catch (IOException | JSONException e) {
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
