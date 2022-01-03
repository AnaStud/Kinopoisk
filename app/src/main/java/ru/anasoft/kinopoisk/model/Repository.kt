package ru.anasoft.kinopoisk.model

interface Repository {
    fun getListOfFilmsFromServer():List<Film>
}