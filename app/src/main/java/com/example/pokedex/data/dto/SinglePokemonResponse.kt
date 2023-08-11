package com.example.pokedex.data.dto

import com.example.pokedex.domain.model.Move
import com.example.pokedex.domain.model.Sprites
import com.example.pokedex.domain.model.Stats
import com.example.pokedex.domain.model.SinglePokemon

data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String,
    val moves: List<Move>
)

fun SinglePokemonResponse.toSinglePokemonModel(): SinglePokemon {
    return SinglePokemon(
        this.sprites,
        this.stats,
        this.height,
        this.weight,
        this.id,
        this.name,
        this.moves
    )
}