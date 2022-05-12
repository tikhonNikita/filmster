package com.nikita.filmapp.adapter.FavouriteFilm

import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.databinding.FavouriteFilmBinding
import com.nikita.filmapp.models.Film


class FavouriteFilmViewHolder(private val itemBinding: FavouriteFilmBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(film: Film) {
        itemBinding.apply {
            ivFavFilmPoster.setImageResource(film.image)
            tvFavFilmTitle.text = film.title
        }
    }
}