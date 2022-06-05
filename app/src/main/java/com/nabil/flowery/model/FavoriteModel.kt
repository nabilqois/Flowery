package com.nabil.flowery.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nabil.flowery.database.Favorite
import com.nabil.flowery.database.FavoriteDao
import com.nabil.flowery.database.FavoriteDatabase

class FavoriteModel(application: Application) : AndroidViewModel(application) {
    private var favDao: FavoriteDao?
    private var favDatabase: FavoriteDatabase? = FavoriteDatabase.getDatabase(application)

    init {
        favDao = favDatabase?.favoriteDao()
    }

    fun getAllFav(): LiveData<List<Favorite>>? {
        return favDao?.getAllFav()
    }
}