package com.example.pokedex.domain.model

data class SinglePokemon(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String,
    val moves: List<Move>
)
