package com.example.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
):ViewModel() {

    fun pokemonPagingData(query: String): Flow<PagingData<PokemonResult>> {
        return getPokemonUseCase(
            GetPokemonUseCase.GetPokemonParams(query, getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    companion object {
        private const val PAGE_SIZE = 20
    }
}