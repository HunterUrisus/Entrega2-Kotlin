package com.example.segundaentrega.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Especie::class, parentColumns = ["id"], childColumns = ["especieId"])
    ]
)
data class Raza(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val especieId: Int
)