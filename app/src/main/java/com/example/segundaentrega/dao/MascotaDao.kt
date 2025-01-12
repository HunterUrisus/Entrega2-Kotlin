package com.example.segundaentrega.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.segundaentrega.data.Mascota

@Dao
interface MascotaDao {
    @Query("SELECT * FROM Mascota")
    suspend fun getAllMascotas(): List<Mascota>

    @Query("SELECT * FROM Mascota WHERE id = :id")
    suspend fun getMascotaById(id: Int): Mascota

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMascota(mascota: Mascota): Long

    @Update
    suspend fun updateMascota(mascota: Mascota)

    @Delete
    suspend fun deleteMascota(mascota: Mascota)
}