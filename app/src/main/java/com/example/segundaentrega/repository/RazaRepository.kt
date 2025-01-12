package com.example.segundaentrega.repository

import com.example.segundaentrega.dao.RazaDao
import com.example.segundaentrega.data.Raza

// Repositorio para Raza
class RazaRepository(private val razaDao: RazaDao) {
    suspend fun getAllRazas(): List<Raza> = razaDao.getAllRazas()
    suspend fun getRazaById(id: Int): Raza = razaDao.getRazaById(id)
    suspend fun insertRaza(raza: Raza): Long = razaDao.insertRaza(raza)
    suspend fun updateRaza(raza: Raza) = razaDao.updateRaza(raza)
    suspend fun deleteRaza(raza: Raza) = razaDao.deleteRaza(raza)
}
