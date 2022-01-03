package ru.anasoft.kinopoisk.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (val id_kinopoisk:Int = 0, val title:String = "Test", val year:Int? = null):Parcelable