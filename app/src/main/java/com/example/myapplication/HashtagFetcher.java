package com.example.myapplication;

import android.os.AsyncTask;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HashtagFetcher extends AsyncTask<String, Void, String> {
    private OkHttpClient client = new OkHttpClient();
    private HashtagFetcherCallback callback;

    // Constructor to receive the callback implementation from MainActivity
    public HashtagFetcher(HashtagFetcherCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        Request request = new Request.Builder()
                .url(Constants.API_URL + params[0])
                .get()
                .addHeader("X-RapidAPI-Key", Constants.API_KEY)
                .addHeader("X-RapidAPI-Host", "hashtagy-generate-hashtags.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (callback != null) {
            callback.onResultReceived(result);
        }
    }
}

