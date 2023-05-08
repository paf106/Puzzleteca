package com.pablo.puzzleteca.data.domain

import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.data.repository.PuzzleRepositoryImpl
import javax.inject.Inject


class GetPuzzlesUseCase @Inject constructor(
    private val repository: PuzzleRepositoryImpl
) {
    suspend operator fun invoke(): List<Puzzle> = repository.getAll()
}

