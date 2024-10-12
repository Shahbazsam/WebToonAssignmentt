package com.example.webtoonassignmentt.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorites)

    @Delete
    suspend fun deleteFavorite(favorites: Favorites)

    @Query("SELECT * FROM Favorites")
    fun getAllFavorites(): Flow<List<Favorites>>

    @Query("SELECT * FROM Favorites WHERE title = :title LIMIT 1")
    suspend fun isFavorite(title: Int): Favorites?

}