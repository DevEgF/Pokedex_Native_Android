package com.example.pokedex.feature.detail

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.R
import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.domain.model.SinglePokemon
import com.example.pokedex.domain.usecase.AddFavoriteUseCase
import com.example.pokedex.domain.usecase.GetSinglePokemonUseCase
import com.example.pokedex.utils.extensions.watchStatus
import com.example.pokedex.utils.extensions.extractId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private val _favoriteUiState = MutableLiveData<FavoriteUiState>()
    val favoriteUiState: LiveData<FavoriteUiState> get() =_favoriteUiState

    init {
        _favoriteUiState.value = FavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
    }

    fun singlePokemon(url: String) = viewModelScope.launch {
        val id = url.extractId()
        getSinglePokemonUseCase
            .invoke(GetSinglePokemonUseCase.GetSinglePokemonParams(id))
            .watchStatus(
                loading = {
                    _uiState.value = UiState.Loading
                },
                success = { data ->
                    _uiState.value = UiState.Success(data)
                },
                error = {
                    _uiState.value = UiState.Error
                }
            )
    }

    fun updateFavorite(pokemonResult: PokemonResult) = viewModelScope.launch {
        pokemonResult.run {
            addFavoriteUseCase.invoke(
                AddFavoriteUseCase.Params(name, url)
            ).watchStatus(
                loading = {
                    _favoriteUiState.value = FavoriteUiState.Loading
                },
                success = {
                    _favoriteUiState.value =
                        FavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_checked)
                },
                error = {
                }
            )
        }
    }

    sealed class UiState {
        object Loading: UiState()
        data class Success (val singlePokemonResponse: SinglePokemon): UiState()
        object Error: UiState()
    }

    sealed class FavoriteUiState {
        object Loading: FavoriteUiState()
        class FavoriteIcon(@DrawableRes val icon: Int): FavoriteUiState()
    }
}