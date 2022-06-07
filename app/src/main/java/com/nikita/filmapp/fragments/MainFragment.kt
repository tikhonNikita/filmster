package com.nikita.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.MainActivity.Companion.DETAILS_RESULT
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.adapter.InteractionHandler
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.fragments.DetailsFragment.Companion.COMM_KEY
import com.nikita.filmapp.fragments.DetailsFragment.Companion.LIKED_KEY
import com.nikita.filmapp.models.Film
import java.lang.Error

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private var _filmModel: FilmsHandler? = null
    private val filmModel get() = _filmModel!!

    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        _filmModel = activity as? FilmsHandler
        if (_filmModel == null) {
            throw Error("No Film Model is provided")
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        parentFragmentManager.setFragmentResultListener(DETAILS_RESULT, this) { _, bundle ->
            val liked = bundle.getBoolean(LIKED_KEY, false)
            val text = bundle.getString(COMM_KEY, "")
            Toast.makeText(
                activity,
                "Film is ${if (liked) "liked" else "disliked"} and: " + text,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun initRecyclerView() {
        binding?.rvFilmList?.apply {
            adapter = FilmsAdapter(filmModel.films, object : InteractionHandler {
                override fun handleClick(film: Film) = handleDetailsItemClick(film)
                override fun addFilm(film: Film): Boolean {
                    val message = "The Films was added to favorites"
                    val undo = resources.getString(R.string.snackbar_undo)
                    filmModel.addToFavourites(film)
                    binding?.apply {
                        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).apply {
                            setAction(undo) {
                                val index = filmModel.films.indexOf(film)
                                filmModel.removeFromFavourites(film)
                                rvFilmList.adapter?.notifyItemChanged(index)
                            }.show()
                        }
                    }

                    return true
                }

                override fun checkIfInList(film: Film) = filmModel.checkIfInFavourites(film)
                override fun removeFromFavourites(film: Film) = filmModel.removeFromFavourites(film)


            })
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun handleDetailsItemClick(film: Film) {
        val act = activity as? MainActivity
        act?.supportActionBar?.hide()
        val layout = requireActivity().findViewById<FrameLayout>(R.id.main_container)
        val padd = layout.paddingTop
        layout.setPadding(0,0,0,0)
        activity?.apply {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, DetailsFragment.create(film, padd))
                .addToBackStack("details")
                .commit()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface FilmsHandler {
    fun addToFavourites(film: Film): Boolean
    fun removeFromFavourites(film: Film): Boolean
    fun checkIfInFavourites(film: Film): Boolean
    val films: MutableList<Film>
    val favouriteFilms: MutableList<Film>
}