package com.nikita.filmapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = filmLists[0]
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
            binding.bntSendComments.setOnClickListener {
                Log.d(TAG, "onViewCreated: ")
            }
        }
    }

    companion object {
        public const val TAG = "DetailsFragment"
    }

}