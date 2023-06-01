package com.example.pokedex.data.network.domain

@Suppress("ConstructorParameterNaming")
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)