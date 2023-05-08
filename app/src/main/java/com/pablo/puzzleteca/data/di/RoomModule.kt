package com.pablo.puzzleteca.data.di

import android.content.Context
import androidx.room.Room
import com.pablo.puzzleteca.data.database.PuzzleDatabase
import com.pablo.puzzleteca.data.model.Puzzle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PuzzleDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: PuzzleDatabase) = db.getDao()
}