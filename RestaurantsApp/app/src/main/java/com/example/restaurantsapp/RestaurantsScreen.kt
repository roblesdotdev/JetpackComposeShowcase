package com.example.restaurantsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantsapp.data.Restaurant

@Composable
fun RestaurantsScreen() {
    val viewModel: RestaurantsViewModel = viewModel()
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        )
    ) {
        items(viewModel.state.value) { restaurant ->
            RestaurantItem(item = restaurant) { id ->
                viewModel.toggleFavorite(id)
            }
        }
    }
}

@Composable
fun RestaurantItem(item: Restaurant, onClick: (id: Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        val favoriteState = rememberSaveable {
            mutableStateOf(false)
        }
        val icon = if (favoriteState.value)
            Icons.Filled.Favorite
        else
            Icons.Filled.FavoriteBorder

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f)
            ) {
                onClick(item.id)
            }
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.85f)
            )
            FavoriteIcon(icon, modifier = Modifier.weight(0.15f)) {
                favoriteState.value = !favoriteState.value
            }
        }
    }
}

@Composable
fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
    )
}

@Composable
fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun FavoriteIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) {
    Image(
        imageVector = icon,
        contentDescription = "Favorite restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestaurantsScreen()
}