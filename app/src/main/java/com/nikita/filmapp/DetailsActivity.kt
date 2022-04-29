package com.nikita.filmapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.filmapp.databinding.ActivityDetailsBinding
import com.nikita.filmapp.models.Film
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val view = binding.root
        //SAFE GET INTENT
        if(intent.hasExtra(DATA_KEY)) {
            val film: Film = Json.decodeFromString(intent.getStringExtra(DATA_KEY).toString())
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
        } else {
            this.onBackPressed()
        }

        binding.bntSendComments.setOnClickListener {
            val data = Intent()
            data.putExtra(MainActivity.COMMENTS, "SOME DATA DATA")
            setResult(RESULT_OK, data)
        }
        setContentView(view)
    }


    companion object {
        public const val DATA_KEY = "FILM_DATA_KEY"
    }

}