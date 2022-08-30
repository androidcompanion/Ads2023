package com.myads2023.ads.adsmodels;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FBAPI {

    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(ConstantAds.fbAdsID)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static ApiInterface apiInterface() {
        return builder(ApiInterface.class);
    }

}
