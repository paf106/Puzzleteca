package com.pablo.puzzleteca.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.puzzleteca.data.domain.DeletePuzzleUseCase
import com.pablo.puzzleteca.data.domain.GetPuzzlesUseCase
import com.pablo.puzzleteca.data.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PuzzleViewModel @Inject constructor(
    private val getPuzzlesUseCase: GetPuzzlesUseCase,
    private val deletePuzzleUseCase: DeletePuzzleUseCase,
) : ViewModel() {

    val puzzles = MutableLiveData<List<Puzzle>?>()

    fun getPuzzles() =
        viewModelScope.launch { withContext(Dispatchers.IO) { puzzles.postValue(getPuzzlesUseCase()) } }

    fun deletePuzzle(puzzle: Puzzle) =
        viewModelScope.launch { withContext(Dispatchers.IO) { deletePuzzleUseCase(puzzle) } }
}