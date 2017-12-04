package com.heepie.soundhub.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunmin on 2017-12-04.
 */

public class RetrofitUtil {
    private static String SERVER_URL = "https://soundhub.che1.co.kr";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if(null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
