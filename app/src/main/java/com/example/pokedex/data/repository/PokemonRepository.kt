package com.example.pokedex.data.repository

import androidx.paging.PagingSource
import com.example.pokedex.domain.model.PokemonResult

 interface PokemonRepository {

    fun getPokemon(queries: String): PagingSource<Int, PokemonResult>
 }