package com.pablo.puzzleteca.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.puzzleteca.data.domain.AddPuzzleUseCase
import com.pablo.puzzleteca.data.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AddPuzzleViewModel @Inject constructor(
    private val addPuzzleUseCase: AddPuzzleUseCase,
) : ViewModel() {

    fun addPuzzle(puzzle: Puzzle) =
        viewModelScope.launch { withContext(Dispatchers.IO) { addPuzzleUseCase(puzzle) } }

}