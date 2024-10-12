package com.example.webtoonassignmentt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webtoonassignmentt.data.WebToon
import com.example.webtoonassignmentt.dataBase.FavoriteDao
import com.example.webtoonassignmentt.dataBase.Favorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {

    private val _favoriteList = MutableStateFlow<List<Favorites>>(emptyList())
    val favoriteList: StateFlow<List<Favorites>> = _favoriteList.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteDao.getAllFavorites().collect { favorites ->
                _favoriteList.value = favorites
                FavoriteManager.favoriteWebtoons.value = favorites.associate { it.title to it.rating }
            }
        }
    }

    suspend fun isFavorite(title: Int): Boolean {
        return favoriteDao.isFavorite(title) != null
    }

    fun onFavoriteClick(webToon: WebToon, rating: Int) {
        viewModelScope.launch {
            val existingFavorite = favoriteDao.isFavorite(webToon.title)

            if (existingFavorite != null) {
                favoriteDao.deleteFavorite(existingFavorite)
                FavoriteManager.favoriteWebtoons.value = FavoriteManager.favoriteWebtoons.value - webToon.title
            } else {
                favoriteDao.insertFavorite(
                    Favorites(
                        title = webToon.title,
                        image = webToon.image,
                        description = webToon.description,
                        rating = rating
                    )
                )
                FavoriteManager.favoriteWebtoons.value = FavoriteManager.favoriteWebtoons.value + (webToon.title to rating)
            }
        }
    }
}