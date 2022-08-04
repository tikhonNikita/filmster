package com.nikita.filmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.filmapp.fragments.DetailsFragment
import com.nikita.filmapp.fragments.FavouritesFragment
import com.nikita.filmapp.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToMainFragment()

        val navigate: BottomNavigationView = findViewById(R.id.navigate)
        navigate.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dmAll -> {
                    goToMainFragment()
                    true
                }
                R.id.dmFavourites -> {
                    goToFavourites()
                    true
                }
                else -> {
                    false
                }
            }
        }


    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, MainFragment())
            .commit()
    }

    private fun goToFavourites() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, FavouritesFragment())
            .commit()
    }

    fun goToDetailsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, DetailsFragment())
            .addToBackStack("DetailsFragment")
            .commit()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
