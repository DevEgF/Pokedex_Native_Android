package com.example.pokedex.data.network.domain

data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String
)