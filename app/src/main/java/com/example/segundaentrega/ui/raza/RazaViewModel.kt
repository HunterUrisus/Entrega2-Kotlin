package com.example.segundaentrega.ui.raza

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segundaentrega.data.Raza
import com.example.segundaentrega.repository.RazaRepository
import kotlinx.coroutines.launch

class RazaViewModel(private val repository: RazaRepository): ViewModel() {

    val razas: LiveData<List<Raza>> = repository.getAllRazas()

    fun addRaza(raza: Raza) {
        viewModelScope.launch {
            repository.insertRaza(raza)
        }
    }

}