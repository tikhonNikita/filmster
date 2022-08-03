package com.nikita.filmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.filmapp.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       goToMainFragment()
    }

        private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, MainFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
