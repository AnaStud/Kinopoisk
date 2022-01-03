package ru.anasoft.kinopoisk.model

class RepositoryImpl:Repository {
    override fun getListOfFilmsFromServer() = getListOfFilms()
}