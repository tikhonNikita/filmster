package com.nikita.filmapp.adapter.FavouriteFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.databinding.FavouriteFilmBinding
import com.nikita.filmapp.models.Film

class FavouriteFilmAdapter(
    private val favorites: List<Film>
) : RecyclerView.Adapter<FavouriteFilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteFilmViewHolder {
        val binding =
            FavouriteFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteFilmViewHolder, position: Int) {
        val film = favorites[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = favorites.size
}