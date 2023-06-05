package com.example.pokedex.data.network.domain

data class Ability(
    val ability: Abilities,
    val is_hidden: Boolean,
    val slot: Int
)