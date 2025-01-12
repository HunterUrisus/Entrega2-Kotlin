package com.example.segundaentrega.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Raza::class, parentColumns = ["id"], childColumns = ["razaId"]),
        ForeignKey(entity = Especie::class, parentColumns = ["id"], childColumns = ["especieId"])
    ]
)
data class Mascota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val fechaNacimiento: String,
    val razaId: Int,
    val especieId: Int
)