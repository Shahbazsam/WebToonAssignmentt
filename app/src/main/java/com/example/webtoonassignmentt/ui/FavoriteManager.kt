package com.example.webtoonassignmentt.ui

import androidx.compose.runtime.mutableStateOf

object FavoriteManager {
    val favoriteWebtoons = mutableStateOf<Map<Int, Int>>(emptyMap())
}