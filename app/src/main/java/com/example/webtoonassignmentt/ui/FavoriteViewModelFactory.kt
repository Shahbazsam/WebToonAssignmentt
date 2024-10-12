package com.example.webtoonassignmentt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webtoonassignmentt.dataBase.FavoriteDatabase

class FavoriteViewModelFactory(private val database: FavoriteDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(database.favoriteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}