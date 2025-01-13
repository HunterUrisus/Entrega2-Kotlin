package com.example.segundaentrega.ui.especie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segundaentrega.data.Especie
import com.example.segundaentrega.repository.EspecieRepository
import kotlinx.coroutines.launch

class EspecieViewModel(private val repository: EspecieRepository) : ViewModel() {

    val especies: LiveData<List<Especie>> = repository.getAllEspecies()

    suspend fun addEspecieAndGetId(especie: Especie): Int {
        return repository.insertEspecieAndGetId(especie)
    }

    fun isEspeciesDatabaseEmpty(callback: (Boolean) -> Unit){
        viewModelScope.launch {
            val isEmpty = repository.isEspecieTableEmpty()
            callback(isEmpty)
        }
    }


}