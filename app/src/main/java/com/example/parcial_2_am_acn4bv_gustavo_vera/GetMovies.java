package com.example.parcial_2_am_acn4bv_gustavo_vera;

import android.os.AsyncTask;
import android.util.Log;

public class GetMovies extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        return url;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("probando_url", s);
    }
}
