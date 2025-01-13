package com.example.segundaentrega.repository

import androidx.lifecycle.LiveData
import com.example.segundaentrega.dao.MascotaDao
import com.example.segundaentrega.data.Mascota

class MascotaRepository(private val mascotaDao: MascotaDao) {
    fun getAllMascotas(): LiveData<List<Mascota>> = mascotaDao.getAllMascotas()

    suspend fun insertMascota(mascota: Mascota) = mascotaDao.insertMascota(mascota)

    suspend fun deleteMascota(mascota: Mascota) = mascotaDao.deleteMascota(mascota)

    suspend fun isMascotaTableEmpty(): Boolean = mascotaDao.isEmpty()
}