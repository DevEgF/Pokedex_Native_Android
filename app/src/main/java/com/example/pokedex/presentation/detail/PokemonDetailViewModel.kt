package com.example.pokedex.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.data.usecase.GetSinglePokemonUseCase
import com.example.pokedex.utils.ResultStatus
import com.example.pokedex.utils.extractId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun singlePokemon(url: String) = viewModelScope.launch {
        val id = url.extractId()
        getSinglePokemonUseCase(GetSinglePokemonUseCase.GetSinglePokemonParams(id))
            .watchStatus()
    }

    private fun Flow<ResultStatus<SinglePokemonResponse>>.watchStatus() = viewModelScope.launch {
        collect { status ->
            _uiState.value = when (status) {
                ResultStatus.Loading -> UiState.Loading
                is ResultStatus.Success -> UiState.Success(status.data)
                is ResultStatus.Error -> UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading: UiState()
        data class Success (val singlePokemonResponse: SinglePokemonResponse): UiState()
        object Error: UiState()
    }
}