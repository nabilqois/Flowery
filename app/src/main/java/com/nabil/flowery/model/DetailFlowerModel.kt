package com.nabil.flowery.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nabil.flowery.database.Favorite
import com.nabil.flowery.database.FavoriteDao
import com.nabil.flowery.database.FavoriteDatabase
import kotlinx.coroutines.launch

class DetailFlowerModel(application: Application): AndroidViewModel(application) {
    private var favDao: FavoriteDao?
    private var favDatabase: FavoriteDatabase? = FavoriteDatabase.getDatabase(application)

    init {
        favDao = favDatabase?.favoriteDao()
    }

    suspend fun checkFav(id: String) = favDao?.checkFav(id)

    fun addFav(_id: String, global_name: String, local_name: String, images: String, date: String) {
        viewModelScope.launch {
            val fav = Favorite(_id, global_name, local_name, images, date)
            favDao?.addFav(fav)
        }
    }

    fun deleteFav(id : String) {
        viewModelScope.launch {
            favDao?.deleteFav(id)
        }
    }
}