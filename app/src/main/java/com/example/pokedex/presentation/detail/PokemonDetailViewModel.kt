package com.example.pokedex.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.pokedex.data.repository.SinglePokemonRepository
import com.example.pokedex.utils.NetworkResource
import com.example.pokedex.utils.extractId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val singlePokemonRepository: SinglePokemonRepository
) : ViewModel() {

    suspend fun getSinglePokemon(url: String) = flow {
        val id = url.extractId()
        emit(NetworkResource.Loading)
        emit(singlePokemonRepository.getSinglePokemon(id))
    }
}