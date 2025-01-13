package com.example.segundaentrega.repository

import androidx.lifecycle.LiveData
import com.example.segundaentrega.dao.RazaDao
import com.example.segundaentrega.data.Raza

class RazaRepository(private val razaDao: RazaDao) {
    fun getAllRazas(): LiveData<List<Raza>> = razaDao.getAllRazas()

    suspend fun insertRaza(raza: Raza)= razaDao.insertRaza(raza)

    suspend fun deleteRaza(raza: Raza) = razaDao.deleteRaza(raza)

    suspend fun insertRazaAndGetId(raza: Raza): Int {
        return razaDao.insertRazaAndGetId(raza).toInt()
    }

}
