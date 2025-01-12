package com.example.segundaentrega.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Especie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)
