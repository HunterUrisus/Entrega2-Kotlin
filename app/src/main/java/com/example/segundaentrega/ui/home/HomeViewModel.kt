package com.example.segundaentrega.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.segundaentrega.data.Mascota

class HomeViewModel : ViewModel() {

    private val _mascotas = MutableLiveData<List<Mascota>>().apply {
        value = listOf(
            Mascota(1, "Fido", "2020-01-01", 1, 1),
            Mascota(2, "Luna", "2019-03-15", 2, 2)
        )
    }
    val mascotas: LiveData<List<Mascota>> = _mascotas
}