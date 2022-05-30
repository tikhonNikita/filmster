package com.nikita.filmapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.filmapp.MainActivity.Companion.DATA_KEY
import com.nikita.filmapp.R
import com.nikita.filmapp.databinding.DetailsFragmentBinding
import com.nikita.filmapp.models.Film
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Error

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rawFilm = arguments?.getString(DATA_KEY) ?: error("DATA IS MANDATORY")
        val film = Json.decodeFromString<Film>(rawFilm)
        binding.apply {
            ivDetails.setImageResource(film.image)
            tvDetailsFilmDescription.text = film.description
            tvDetailsFilmTitle.text = film.title
            btnAddFriend.setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go and watch this film \"${film.title}\"")
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
        }
    }
}