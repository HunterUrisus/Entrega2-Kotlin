package com.example.segundaentrega.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.segundaentrega.data.Especie

@Dao
interface EspecieDao {
    @Query("SELECT * FROM Especie")
    suspend fun getAllEspecies(): List<Especie>

    @Query("SELECT * FROM Especie WHERE id = :id")
    suspend fun getEspecieById(id: Int): Especie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEspecie(especie: Especie): Long

    @Update
    suspend fun updateEspecie(especie: Especie)

    @Delete
    suspend fun deleteEspecie(especie: Especie)
}