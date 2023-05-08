package com.pablo.puzzleteca.data.repository

import androidx.lifecycle.LiveData
import com.pablo.puzzleteca.data.model.Puzzle
import kotlinx.coroutines.flow.Flow

interface PuzzleRepository {
    suspend fun insert(puzzle: Puzzle)
    suspend fun delete(puzzle: Puzzle)
    suspend fun deleteAll()
    suspend fun getAll(): List<Puzzle>
    fun getFavourites(): List<Puzzle>
    fun setPuzzleFavourite(puzzleId: Int, isFavourite: Int)
}