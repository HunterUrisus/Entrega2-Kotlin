package com.example.segundaentrega.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.segundaentrega.data.Raza

@Dao
interface RazaDao {
    @Query("SELECT * FROM Raza")
    fun getAllRazas(): LiveData<List<Raza>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRaza(raza: Raza)

    @Delete
    suspend fun deleteRaza(raza: Raza)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRazaAndGetId(raza: Raza): Long
}