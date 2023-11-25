package com.example.myapplication;

import java.util.List;

public class HashtagResponse {
    Data data;

    public static class Data {
        Best30Hashtags best_30_hashtags;

        public static class Best30Hashtags {
            List<String> hashtags;
        }
    }
}