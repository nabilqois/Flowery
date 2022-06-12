package com.nabil.flowery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllFav(): LiveData<List<Favorite>>

    @Query("SELECT count(*) FROM favorite WHERE favorite._id = :id")
    suspend fun checkFav(id: String) : Int

    @Insert
    suspend fun addFav(favoriteEntity: Favorite)

    @Query("DELETE FROM favorite WHERE favorite._id = :id")
    suspend fun deleteFav(id: String) : Int
}