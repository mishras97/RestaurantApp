package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantapp.data.Result;

import java.util.List;

public class RestaurantViewModel extends ViewModel {
    public MutableLiveData<List<Result>> results;
    private RestaurantRepository restaurantRepository;

    public RestaurantViewModel() {

    }

    public void setContext(@NonNull String location,@NonNull int radius,@NonNull String type,@NonNull String key) {
        if (restaurantRepository == null) {
            restaurantRepository = RestaurantRepository.getInstance();
        }
        results = restaurantRepository.loadInitialRestaurants(location, radius, type, key);
    }

    public void getRestaurants(@NonNull String location, @NonNull int radius, @NonNull String type, @NonNull String keyword, @NonNull String key) {
        results = restaurantRepository.getRestaurants(location, radius, type, keyword, key);
    }
}
