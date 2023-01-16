package com.nikita.filmapp.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun FavouriteTitle(title: String) {
    Text(text = title, textAlign = TextAlign.Center)
}