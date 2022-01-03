package ru.anasoft.kinopoisk.model

interface Repository {
    fun getFilmFromServer():Film
}