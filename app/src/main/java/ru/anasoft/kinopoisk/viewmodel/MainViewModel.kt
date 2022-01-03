package ru.anasoft.kinopoisk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.anasoft.kinopoisk.model.RepositoryImpl

class MainViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()): ViewModel() {

    private val repositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl()
    }

    fun getLiveData() = liveData

    fun getListOfFilms() {
        liveData.value = AppState.Loading(50)
        Thread {
            Thread.sleep(1000)
            liveData.postValue(AppState.Success(repositoryImpl.getListOfFilmsFromServer()))
        }.start()
    }

}