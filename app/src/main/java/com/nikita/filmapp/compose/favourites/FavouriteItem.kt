package com.nikita.filmapp.compose.favourites


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.codelab.theming.ui.start.theme.FilmAppShapes
import com.nikita.filmapp.theme.uiKit.components.FilmImage
import com.nikita.filmapp.theme.uiKit.containers.ElevatedSurface


@Composable
fun FavouriteItem(title: String, imageURL: String, modifier: Modifier = Modifier) {
    return ElevatedSurface {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
                .height(120.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(FilmAppShapes.large)
                    .size(120.dp)
            ) {
                FilmImage(imageURL = imageURL, size = 120.dp)
            }
            FavouriteTitle(title = title)
        }
    }
}