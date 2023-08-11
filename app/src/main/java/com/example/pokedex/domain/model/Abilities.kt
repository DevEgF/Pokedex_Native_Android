package com.example.pokedex.domain.model

@Suppress("ConstructorParameterNaming")
data class Abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)