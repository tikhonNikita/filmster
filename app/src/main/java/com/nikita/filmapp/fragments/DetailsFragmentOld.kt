//package com.nikita.filmapp.fragments
//
//import android.content.Intent
//import android.graphics.Color
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import androidx.fragment.app.Fragment
//import com.nikita.filmapp.MainActivityOld
//import com.nikita.filmapp.MainActivityOld.Companion.DATA_KEY
//import com.nikita.filmapp.MainActivityOld.Companion.DETAILS_RESULT
//import com.nikita.filmapp.R
//import com.nikita.filmapp.databinding.DetailsFragmentBinding
//import com.nikita.filmapp.models.Film
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//
//class DetailsFragment : Fragment() {
//
//    private var _binding: DetailsFragmentBinding? = null
//    private val bundle = Bundle()
//    private var statusBarColor: Int? = null
//    private var prevPadding: Int? = null
//
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        statusBarColor = activity?.window?.statusBarColor
//        activity?.window?.statusBarColor = Color.TRANSPARENT
//        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val rawFilm = arguments?.getString(DATA_KEY) ?: error("DATA IS MANDATORY")
//        prevPadding = arguments?.getInt(PREV_PADDING) ?: 100
//        val film = Json.decodeFromString<Film>(rawFilm)
//        binding.apply {
//            ivDetails.setImageResource(film.image)
//            tvDetailsFilmDescription.text = film.description
//            tvDetailsFilmTitle.text = film.title
//            tbDetails.title = film.title
//            btnAddFriend.setOnClickListener {
//                val sendIntent = Intent()
//                sendIntent.action = Intent.ACTION_SEND
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go and watch this film \"${film.title}\"")
//                sendIntent.type = "text/plain"
//
//                startActivity(sendIntent)
//            }
//            binding.bntSendComments.setOnClickListener {
//                val data = binding.etDetails.text.toString()
//                bundle.putString(COMM_KEY, data)
//                bundle.putBoolean(LIKED_KEY, binding.cbDetails.isChecked)
//            }
//        }
//    }
//
//
//    override fun onDestroy() {
//        activity?.window?.statusBarColor = statusBarColor ?: R.color.purple_700
//        val act = activity as? MainActivityOld
//        act?.supportActionBar?.show()
//        val layout = requireActivity().findViewById<FrameLayout>(R.id.main_container)
//        layout.setPadding(0, prevPadding!!, 0, 0)
//        parentFragmentManager.setFragmentResult(DETAILS_RESULT, bundle)
//        super.onDestroy()
//    }
//
//
//    companion object {
//        fun create(film: Film, prevPadding: Int): DetailsFragment {
//            val stringedFilm = Json.encodeToString(film)
//            val fragmentToGo = DetailsFragment()
//            val args = Bundle().apply {
//                putString(DATA_KEY, stringedFilm)
//                putInt("PDD", prevPadding)
//            }
//            fragmentToGo.arguments = args
//            return fragmentToGo
//        }
//
//        const val COMM_KEY = "COMM_KEY"
//        const val LIKED_KEY = "LIKED_KEY"
//        const val PREV_PADDING = "PDD"
//    }
//}