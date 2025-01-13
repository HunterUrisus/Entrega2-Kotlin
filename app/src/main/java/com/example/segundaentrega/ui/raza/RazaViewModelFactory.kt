package com.example.segundaentrega.ui.raza

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.segundaentrega.repository.RazaRepository

class RazaViewModelFactory(private val repository: RazaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RazaViewModel::class.java)){
            return RazaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}