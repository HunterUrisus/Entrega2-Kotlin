package com.example.segundaentrega.repository

import com.example.segundaentrega.dao.EspecieDao
import com.example.segundaentrega.data.Especie

class EspecieRepository(private val especieDao: EspecieDao) {
    suspend fun getAllEspecies(): List<Especie> = especieDao.getAllEspecies()
    suspend fun getEspecieById(id: Int): Especie = especieDao.getEspecieById(id)
    suspend fun insertEspecie(especie: Especie): Long = especieDao.insertEspecie(especie)
    suspend fun updateEspecie(especie: Especie) = especieDao.updateEspecie(especie)
    suspend fun deleteEspecie(especie: Especie) = especieDao.deleteEspecie(especie)
}