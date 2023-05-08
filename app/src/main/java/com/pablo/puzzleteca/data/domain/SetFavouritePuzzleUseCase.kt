package com.pablo.puzzleteca.data.domain


import com.pablo.puzzleteca.data.repository.PuzzleRepositoryImpl
import javax.inject.Inject


class SetFavouritePuzzleUseCase @Inject constructor(
    private val repository: PuzzleRepositoryImpl
) {
    suspend operator fun invoke(puzzleId: Int, isFavourite: Int) =
        repository.setPuzzleFavourite(puzzleId, isFavourite)
}

