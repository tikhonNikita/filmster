package com.nikita.filmapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.databinding.FilmCardBinding
import com.nikita.filmapp.models.Film


private const val TAG = "FILMDS_ADAPTER"

class FilmsAdapter(
    private val dataSet: List<Film>,
    private val mainActivity: MainActivity,
    private val handlePress: () -> Unit
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
                    Log.d("TAG", "bind: bo to film details")
                    mainActivity.goToDetailsFragment()
                }
                ibLike.setImageResource(R.drawable.ic_star_like)

                ibLike.setOnClickListener {
                    handlePress()
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
