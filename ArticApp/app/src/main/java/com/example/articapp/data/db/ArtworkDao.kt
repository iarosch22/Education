package com.example.articapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworks(artworks: List<ArtWorkDbEntity>)

    @Query("SELECT * FROM artwork_table")
    suspend fun getArtworks(): List<ArtWorkDbEntity>

}