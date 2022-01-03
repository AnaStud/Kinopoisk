package ru.anasoft.kinopoisk.viewmodel

import ru.anasoft.kinopoisk.model.Film

sealed class AppState {
    data class Loading(val progress:Int):AppState()
    data class Success(val listOfFilms: List<Film>):AppState()
    data class Error(val error:Throwable):AppState()
}