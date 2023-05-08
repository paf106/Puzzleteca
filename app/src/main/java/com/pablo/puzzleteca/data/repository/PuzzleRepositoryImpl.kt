package com.pablo.puzzleteca.data.repository


import androidx.lifecycle.LiveData
import com.pablo.puzzleteca.data.database.dao.PuzzleDao
import com.pablo.puzzleteca.data.model.Puzzle
import javax.inject.Inject

class PuzzleRepositoryImpl @Inject constructor(
    private val dao: PuzzleDao
) : PuzzleRepository {

    override suspend fun insert(puzzle: Puzzle) = dao.insert(puzzle)

    override suspend fun delete(puzzle: Puzzle) = dao.delete(puzzle)

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun getAll(): List<Puzzle> = dao.getAll()

    override fun getFavourites(): List<Puzzle> = dao.getFavourites()

    override fun setPuzzleFavourite(puzzleId: Int, isFavourite: Int) =
        dao.setPuzzleFavourite(puzzleId, isFavourite)
}