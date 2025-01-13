package com.example.segundaentrega.data

data class MascotaWithDetails(
    val id: Int,               // ID de la mascota
    val nombre: String,        // Nombre de la mascota
    val fechaNacimiento: String, // Fecha de nacimiento de la mascota
    val especieNombre: String, // Nombre de la especie (obtenido del mapping)
    val razaNombre: String     // Nombre de la raza (obtenido del mapping)
) {

}