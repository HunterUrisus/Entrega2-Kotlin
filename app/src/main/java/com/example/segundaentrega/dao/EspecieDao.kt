package com.example.segundaentrega.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.segundaentrega.data.Especie

@Dao
interface EspecieDao {
    @Query("SELECT * FROM Especie")
    fun getAllEspecies(): LiveData<List<Especie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEspecie(especie: Especie)

    @Delete
    suspend fun deleteEspecie(especie: Especie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEspecieAndGetId(especie: Especie): Long

    @Query("SELECT COUNT(*) FROM Especie")
    suspend fun isEspecieEmpty(): Boolean = countEspeciesSuspend() == 0

    @Query("SELECT COUNT(*) FROM Mascota")
    suspend fun countEspeciesSuspend(): Int
}