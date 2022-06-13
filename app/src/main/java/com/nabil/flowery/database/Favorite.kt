package com.nabil.flowery.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val _id: String,

    val global_name: String,

    val local_name: String,

    val images: String,

    val date: String
):Serializable
