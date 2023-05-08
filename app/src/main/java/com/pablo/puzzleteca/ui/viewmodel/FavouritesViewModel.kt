package com.pablo.puzzleteca.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.puzzleteca.data.domain.GetFavouritePuzzlesUseCase
import com.pablo.puzzleteca.data.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritePuzzlesUseCase: GetFavouritePuzzlesUseCase,

) : ViewModel() {

    val favouritesPuzzles = MutableLiveData<List<Puzzle>?>()


    fun onCreate() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { favouritesPuzzles.postValue(getFavouritePuzzlesUseCase()) }
        }
    }

}