package com.pablo.puzzleteca.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pablo.puzzleteca.data.database.dao.PuzzleDao
import com.pablo.puzzleteca.data.model.Puzzle

@Database(entities = [Puzzle::class], version = 2, exportSchema = false)
abstract class PuzzleDatabase : RoomDatabase() {

    abstract fun getDao(): PuzzleDao

    companion object {
        @Volatile
        private var INSTANCE: PuzzleDatabase? = null

        fun getInstance(context: Context): PuzzleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PuzzleDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}