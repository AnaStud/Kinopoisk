package ru.anasoft.kinopoisk.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (val id_kinopoisk:Int = 0, val title:String = "Test", val year:Int? = null):Parcelable

fun getListOfFilms() = listOf(
    Film(0,"Test1"),
    Film(0,"Test2"),
    Film(0,"Test3"),
    Film(0,"Test4"),
    Film(0,"Test5"),
    Film(0,"Test6"),
    Film(0,"Test7"),
    Film(0,"Test8"),
    Film(0,"Test9"),
    Film(0,"Test10")
)