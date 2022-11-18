package com.nikita.filmapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.databinding.DetailsFragmentBinding
import com.nikita.filmapp.models.filmLists


private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        val film = filmLists[1]
        binding.apply {
            ivDetails.setImageResource(film.image)
            tvDetailsFilmDescription.text = film.description
            tvDetailsFilmTitle.text = film.title
            tbDetails.title = film.title
            btnAddFriend.setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go and watch this film \"${film.title}\"")
                sendIntent.type = "text/plain"

                startActivity(sendIntent)
            }
            binding.btnRateTheFilm.setOnClickListener {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        public const val TAG = "DetailsFragment"
    }

}