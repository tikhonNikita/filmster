package com.nikita.filmapp.adapter.FavouriteFilm

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.compose.favourites.FavouriteItem
import com.nikita.filmapp.databinding.FavouriteFilmBinding
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.theme.FilmAppTheme
import com.nikita.filmapp.utils.IMG_URL


class FavouriteFilmViewHolder(
    private val itemBinding: FavouriteFilmBinding,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(film: Movie) {
        itemBinding.apply {
            composeView.setContent {
                FilmAppTheme {
                    FavouriteItem(
                        title = film.title,
                        imageURL = IMG_URL + film.poster,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}