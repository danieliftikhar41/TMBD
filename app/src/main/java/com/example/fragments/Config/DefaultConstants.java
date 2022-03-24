package com.example.fragments.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultConstants {

        public static final String API_KEY = "14c9d935480e19cfe127833e176b998d";
    public static final String SESSION_ID = "f3160213b50bf01698d0339b8373d3cfe9f17449";
    public static final String ACCOUNT_ID = "MDanilh";

    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/";

    public static final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.themoviedb.org/3/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

}
