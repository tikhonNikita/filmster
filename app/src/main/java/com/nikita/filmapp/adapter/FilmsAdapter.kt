package com.nikita.filmapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.R
import com.nikita.filmapp.databinding.FilmCardBinding
import com.nikita.filmapp.models.Film

class FilmsAdapter(
    private val dataSet: List<Film>,
    private val interactionHandler: InteractionHandler
) :
    RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {
    private lateinit var context: Context

    inner class FilmViewHolder(private val itemBinding: FilmCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(film: Film) {
            itemBinding.apply {
                tvFilmTitle.text = film.title
                ivFilmCard.setImageResource(film.image)
                itemBinding.tvFilmTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
                btnGoToFilmDetails.setOnClickListener {
                    film.selected = !film.selected
                    interactionHandler.handleClick(film)
                }
                if (interactionHandler.checkIfInList(film)) {
                    ibLike.setImageResource(R.drawable.ic_star_filled)
                } else {
                    ibLike.setImageResource(R.drawable.ic_star_like)
                }


                ibLike.setOnClickListener {
                    val inList = interactionHandler.checkIfInList(film)
                    if (inList) {
                        ibLike.setImageResource(R.drawable.ic_star_like)
                        interactionHandler.removeFromFavourites(film)
                    } else {
                        ibLike.setImageResource(R.drawable.ic_star_filled)
                        interactionHandler.addFilm(film)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.FilmViewHolder {
        context = parent.context
        val binding = FilmCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsAdapter.FilmViewHolder, position: Int) {
        val film = dataSet[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = dataSet.size
}

interface InteractionHandler {
    fun handleClick(film: Film)
    fun addFilm(film: Film): Boolean
    fun checkIfInList(film: Film): Boolean
    fun removeFromFavourites(film: Film): Boolean
}

// TODO:
//  Add display of new activity with film data
//  Add button to invite a new friend
//  Add comments field
// Add saving in cache?