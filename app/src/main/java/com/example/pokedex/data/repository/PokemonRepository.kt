package com.example.pokedex.data.repository

import androidx.paging.PagingSource
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.data.usecase.base.ResultStatus

 interface PokemonRepository {

    fun getPokemon(queries: String): PagingSource<Int, PokemonResult>
 }