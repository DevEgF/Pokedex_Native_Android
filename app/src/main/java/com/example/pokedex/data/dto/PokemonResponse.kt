package com.example.pokedex.data.dto

import com.example.pokedex.domain.model.PokemonResult

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)
