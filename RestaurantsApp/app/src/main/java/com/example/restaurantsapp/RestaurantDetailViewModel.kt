package com.example.restaurantsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsapp.data.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantDetailViewModel : ViewModel() {
    private var restInterface: RestaurantsApiService
    val state = mutableStateOf<Restaurant?>(null)

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jc-restaurants-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)
        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(2)
            state.value = restaurant
        }
    }

    private suspend fun getRemoteRestaurant(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val responseMap = restInterface.getRestaurant(id)
            return@withContext responseMap.values.first() 
        }
    }
}