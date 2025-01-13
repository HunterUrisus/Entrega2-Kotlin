package com.example.segundaentrega.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.data.MascotaWithDetails

@Dao
interface MascotaDao {
    @Query("SELECT * FROM Mascota")
    fun getAllMascotas(): LiveData<List<Mascota>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMascota(mascota: Mascota)

    @Delete
    suspend fun deleteMascota(mascota: Mascota)

    @Query("SELECT COUNT(*) FROM Mascota")
    suspend fun isEmpty(): Boolean = countMascotasSuspend() == 0

    @Query("SELECT COUNT(*) FROM Mascota")
    suspend fun countMascotasSuspend(): Int

    @Query("""
    SELECT Mascota.*, Especie.nombre AS especieNombre, Raza.nombre AS razaNombre
    FROM Mascota
    INNER JOIN Especie ON Mascota.especieId = Especie.id
    INNER JOIN Raza ON Mascota.razaId = Raza.id
    """)
    fun getAllMascotasWithDetails(): LiveData<List<MascotaWithDetails>>



}