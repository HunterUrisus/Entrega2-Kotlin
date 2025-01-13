package com.example.segundaentrega.ui.especie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.segundaentrega.repository.EspecieRepository

class EspecieViewModelFactory(private val repository: EspecieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EspecieViewModel::class.java)) {
            return EspecieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}