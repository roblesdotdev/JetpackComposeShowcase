package com.example.restaurantsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.restaurantsapp.ui.theme.RestaurantsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsAppTheme {
                RestaurantsScreen()
            }
        }
    }
}

