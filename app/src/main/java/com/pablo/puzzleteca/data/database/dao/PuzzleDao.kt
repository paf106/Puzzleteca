package com.pablo.puzzleteca.data.database.dao

import androidx.room.*
import com.pablo.puzzleteca.data.model.Puzzle


@Dao
interface PuzzleDao {

    @Query("SELECT * FROM puzzle_table")
    fun getAll(): List<Puzzle>

    @Query("SELECT * FROM puzzle_table where isFavourite = 1")
    fun getFavourites(): List<Puzzle>

    @Query("UPDATE puzzle_table SET isFavourite = :isFavourite where idPuzzle = :puzzleId")
    fun setPuzzleFavourite(puzzleId: Int, isFavourite: Int)

    @Query("DELETE FROM puzzle_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(puzzle: Puzzle)

    @Delete
    suspend fun delete(puzzle: Puzzle)
}