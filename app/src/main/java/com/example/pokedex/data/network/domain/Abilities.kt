package com.example.pokedex.data.network.domain

@Suppress("ConstructorParameterNaming")
data class Abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)