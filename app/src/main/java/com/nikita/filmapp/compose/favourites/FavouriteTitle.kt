package com.nikita.filmapp.compose.favourites

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.nikita.filmapp.theme.FilmAppTypography

@Composable
fun FavouriteTitle(title: String) {
    Text(text = title, textAlign = TextAlign.Center, style = FilmAppTypography.titleSmall)
}