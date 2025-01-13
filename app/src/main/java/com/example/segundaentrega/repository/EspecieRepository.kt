package com.example.segundaentrega.repository

import androidx.lifecycle.LiveData
import com.example.segundaentrega.dao.EspecieDao
import com.example.segundaentrega.data.Especie

class EspecieRepository(private val especieDao: EspecieDao) {
    fun getAllEspecies(): LiveData<List<Especie>> = especieDao.getAllEspecies()

    suspend fun insertEspecieAndGetId(especie: Especie): Int {
        return especieDao.insertEspecieAndGetId(especie).toInt()
    }

    suspend fun isEspecieTableEmpty(): Boolean = especieDao.isEspecieEmpty()
}