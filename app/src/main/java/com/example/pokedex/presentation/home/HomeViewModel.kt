package com.example.pokedex.presentation.home

import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.utils.base.NetworkResource
import com.example.pokedex.utils.extractId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
):ViewModel() {

    private var currentResult: Flow<PagingData<PokemonResult>>? = null
    fun getPokemons(queries: String): Flow<PagingData<PokemonResult>> {
        val newResult: Flow<PagingData<PokemonResult>> =
            pokemonRepository.getPokemon(queries).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}