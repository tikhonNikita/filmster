package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikita.filmapp.R
import com.nikita.filmapp.models.Film

class FilmViewModel : ViewModel() {
    private val films = MutableLiveData<MutableList<Film>>()

    val filmL: LiveData<MutableList<Film>>
        get() = films


    fun updateFilms() {
        val tempList = mutableListOf<Film>()
        films.value?.let { tempList.addAll(it) }
        tempList.add(
            Film(
                30,
                "SomeTest",
                R.drawable.multiverse_of_madness,
                "Thi is a cool film",
                false
            )
        )
        films.postValue(tempList)
    }


    fun addFirstFilm(title: String) {
        val newList = films.value?.toMutableList()
        newList?.run {
            add(
                Film(
                    30,
                    title,
                    R.drawable.multiverse_of_madness,
                    "Thi is a cool film",
                    false
                )
            )
            films.postValue(this)
        }
    }
}