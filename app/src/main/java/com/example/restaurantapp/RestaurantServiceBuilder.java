package com.example.restaurantapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantServiceBuilder {
    public static final String BASE_URL = "https://maps.googleapis.com";

    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);

    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build());

    private Retrofit retrofit = builder.build();

    public RestaurantAPI buildService(Class<RestaurantAPI> serviceType) {
        return retrofit.create(serviceType);
    }

}
