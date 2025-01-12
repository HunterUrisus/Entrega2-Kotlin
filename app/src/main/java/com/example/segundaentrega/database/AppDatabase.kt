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
    entities = [Mascota::class, Raza::class, Especie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao
    abstract fun razaDao(): RazaDao
    abstract fun especieDao(): EspecieDao

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