package com.nikita.filmapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.DetailsActivity
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.databinding.FilmCardBinding
import com.nikita.filmapp.models.Film
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
                handleTextChange(film, tvFilmTitle)
                btnGoToFilmDetails.setOnClickListener {
                    film.selected = !film.selected
                    handleTextChange(film, tvFilmTitle)
                    interactionHandler.handleClick(film)
                }

                ibLike.setOnClickListener {
                    val inList = interactionHandler.addFilm(film)
                    if (inList) {
                        ibLike.setImageResource(R.drawable.ic_star_filled)
                    } else {
                        ibLike.setImageResource(R.drawable.ic_star_like)
                    }
                }
            }
        }

        private fun handleTextChange(film: Film, textView: TextView) {
            if (film.selected) {
                textView.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
            } else {
                textView.setTextColor(ContextCompat.getColor(context, R.color.black))

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
    fun handleClick (film: Film)
    fun addFilm (film: Film): Boolean
}

// TODO:
//  Add display of new activity with film data
//  Add button to invite a new friend
//  Add comments field
// Add saving in cache?