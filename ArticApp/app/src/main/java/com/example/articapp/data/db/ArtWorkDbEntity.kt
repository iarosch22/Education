package com.example.articapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork_table")
data class ArtWorkDbEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,

)
