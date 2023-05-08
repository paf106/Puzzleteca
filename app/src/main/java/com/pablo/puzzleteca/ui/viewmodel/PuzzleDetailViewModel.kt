package com.pablo.puzzleteca.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.puzzleteca.data.domain.GetPuzzlesUseCase
import com.pablo.puzzleteca.data.domain.SetFavouritePuzzleUseCase
import com.pablo.puzzleteca.data.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PuzzleDetailViewModel @Inject constructor(
    private val setFavouritePuzzleUseCase: SetFavouritePuzzleUseCase,
) : ViewModel() {

    var isFavourite = MutableLiveData<Boolean>()

    fun setFavourite(puzzle: Puzzle) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (puzzle.isFavourite) {
                    // Si el puzzle es favorito lo cambiamos
                    setFavouritePuzzleUseCase(puzzle.idPuzzle, 0)
                    isFavourite.postValue(false)
                } else {
                    setFavouritePuzzleUseCase(puzzle.idPuzzle, 1)
                    isFavourite.postValue(true)
                }

            }
        }
    }
}