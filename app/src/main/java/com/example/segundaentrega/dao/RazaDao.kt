package com.example.segundaentrega.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.segundaentrega.data.Raza

@Dao
interface RazaDao {
    @Query("SELECT * FROM Raza")
    suspend fun getAllRazas(): List<Raza>

    @Query("SELECT * FROM Raza WHERE id = :id")
    suspend fun getRazaById(id: Int): Raza

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRaza(raza: Raza): Long

    @Update
    suspend fun updateRaza(raza: Raza)

    @Delete
    suspend fun deleteRaza(raza: Raza)
}