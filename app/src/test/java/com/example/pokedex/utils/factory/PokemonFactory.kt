package com.example.pokedex.utils.factory

import com.example.pokedex.domain.model.PokemonResult

class PokemonFactory {

    fun create(pokemonTest: PokemonTest) = when (pokemonTest) {
        PokemonTest.Pokemon1 -> PokemonResult(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )

        PokemonTest.Pokemon2 -> PokemonResult(
            name = "ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/"
        )
    }

    sealed class PokemonTest {
        object Pokemon1 : PokemonTest()
        object Pokemon2 : PokemonTest()
    }
}