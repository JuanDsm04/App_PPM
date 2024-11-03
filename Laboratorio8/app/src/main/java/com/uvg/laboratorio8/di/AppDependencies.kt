package com.uvg.laboratorio8.di

import android.content.Context
import androidx.room.Room
import com.uvg.laboratorio8.data.local.AppDatabase

object AppDependencies {
    private var database: AppDatabase? = null

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "labs.db"
        ).build()
    }

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}