package com.pablo.puzzleteca.data.domain

import androidx.lifecycle.LiveData
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.data.repository.PuzzleRepositoryImpl
import javax.inject.Inject

class GetFavouritePuzzlesUseCase @Inject constructor(
    private val repository: PuzzleRepositoryImpl
    ){
    suspend operator fun invoke(): List<Puzzle> = repository.getFavourites()
}