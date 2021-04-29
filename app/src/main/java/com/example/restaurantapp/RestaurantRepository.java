package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.restaurantapp.data.PlaceResult;
import com.example.restaurantapp.data.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    private static RestaurantRepository restaurantRepository;
    public MutableLiveData<List<Result>> mResult = new MutableLiveData<>();

    public static RestaurantRepository getInstance() {
        if (restaurantRepository == null) {
            restaurantRepository = new RestaurantRepository();
        }
        return restaurantRepository;
    }

    public MutableLiveData<List<Result>> getRestaurants(@NonNull String location,@NonNull int radius,@NonNull String type,@NonNull String keyword,@NonNull String key) {
        final RestaurantAPI restaurantAPI = new RestaurantServiceBuilder().buildService(RestaurantAPI.class);
        final Call<PlaceResult> call = restaurantAPI.getRestaurants(location, radius, type, keyword, key);
        call.enqueue(new Callback<PlaceResult>() {
            @Override
            public void onResponse(Call<PlaceResult> call, Response<PlaceResult> response) {
                if (response.isSuccessful()) {
                    final PlaceResult placeResult = response.body();
                    final List<Result> results = placeResult.getResults();
                    mResult.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<PlaceResult> call, Throwable t) {

            }
        });
        return mResult;
    }

    public MutableLiveData<List<Result>> loadInitialRestaurants(@NonNull String location,@NonNull int radius,@NonNull String type, @NonNull String key) {
        final RestaurantAPI restaurantAPI = new RestaurantServiceBuilder().buildService(RestaurantAPI.class);
        final Call<PlaceResult> call = restaurantAPI.getInitialRestaurants(location, radius, type, key);
        call.enqueue(new Callback<PlaceResult>() {
            @Override
            public void onResponse(Call<PlaceResult> call, Response<PlaceResult> response) {
                if (response.isSuccessful()) {
                    final PlaceResult placeResult = response.body();
                    final List<Result> results = placeResult.getResults();
                    mResult.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<PlaceResult> call, Throwable t) {

            }
        });
        return mResult;
    }

}
