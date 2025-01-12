package com.example.segundaentrega.repository

import com.example.segundaentrega.dao.MascotaDao
import com.example.segundaentrega.data.Mascota

class MascotaRepository(private val mascotaDao: MascotaDao) {
    suspend fun getAllMascotas(): List<Mascota> = mascotaDao.getAllMascotas()
    suspend fun getMascotaById(id: Int): Mascota = mascotaDao.getMascotaById(id)
    suspend fun insertMascota(mascota: Mascota): Long = mascotaDao.insertMascota(mascota)
    suspend fun updateMascota(mascota: Mascota) = mascotaDao.updateMascota(mascota)
    suspend fun deleteMascota(mascota: Mascota) = mascotaDao.deleteMascota(mascota)
}