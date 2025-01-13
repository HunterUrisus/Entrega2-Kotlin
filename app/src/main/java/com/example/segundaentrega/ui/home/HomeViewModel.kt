package com.example.segundaentrega.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.repository.MascotaRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MascotaRepository) : ViewModel() {

    val mascotas: LiveData<List<Mascota>> = repository.getAllMascotas()

    fun addMascota(mascota: Mascota) {
        viewModelScope.launch {
            repository.insertMascota(mascota)
        }
    }
}
