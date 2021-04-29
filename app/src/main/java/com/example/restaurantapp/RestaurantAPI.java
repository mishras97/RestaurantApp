package com.example.restaurantapp;

import com.example.restaurantapp.data.PlaceResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantAPI {

    @GET("/maps/api/place/nearbysearch/json?")
    public Call<PlaceResult> getRestaurants(@Query("location") String location,
                                            @Query("radius") int radius,
                                            @Query("type") String type,
                                            @Query("keyword") String keyword,
                                            @Query("key") String key);

    @GET("/maps/api/place/nearbysearch/json?")
    public Call<PlaceResult> getInitialRestaurants(@Query("location") String location,
                                                   @Query("radius") int radius,
                                                   @Query("type") String type,
                                                   @Query("key") String key);
}
