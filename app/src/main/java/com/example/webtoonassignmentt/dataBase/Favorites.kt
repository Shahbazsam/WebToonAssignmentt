package com.example.webtoonassignmentt.dataBase

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @StringRes val title : Int,
    @DrawableRes val image : Int,
    @StringRes val description : Int,
    val rating : Int,

)
