package com.example.segundaentrega.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.segundaentrega.dao.EspecieDao
import com.example.segundaentrega.dao.MascotaDao
import com.example.segundaentrega.dao.RazaDao
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.data.Raza
import com.example.segundaentrega.data.Especie

@Database(
    entities = [Especie::class, Mascota::class, Raza::class], // Lista de entidades
    version = 1, // Versión actual de la base de datos
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mascotaDao(): MascotaDao
    abstract fun EspecieDao(): EspecieDao
    abstract fun RazaDao(): RazaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}