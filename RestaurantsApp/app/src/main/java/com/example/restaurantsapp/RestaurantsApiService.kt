package com.example.restaurantsapp

import com.example.restaurantsapp.data.Restaurant
import retrofit2.http.GET

interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>
}