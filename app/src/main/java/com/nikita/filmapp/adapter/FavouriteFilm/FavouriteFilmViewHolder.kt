package com.nikita.filmapp.adapter.FavouriteFilm

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.filmapp.databinding.FavouriteFilmBinding
import com.nikita.filmapp.models.IMG_URL
import com.nikita.filmapp.models.Movie


class FavouriteFilmViewHolder(
    private val itemBinding: FavouriteFilmBinding,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(film: Movie) {
        itemBinding.apply {
            Glide.with(context).load(IMG_URL + film.poster)
                .into(ivFavFilmPoster)
            tvFavFilmTitle.text = film.title
        }
    }
}