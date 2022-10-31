package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.R
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.services.api.RetrofitInstance
import kotlinx.coroutines.launch

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

    fun getFilm() {
        viewModelScope.launch {
            val data = RetrofitInstance.api.getTrending()
            data.body()?.let {
                addFirstFilm(title = it.results[0].title)
            }
        }
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