package com.example.pokedex.utils

import android.view.View
import com.example.pokedex.data.network.domain.PokemonResult

typealias PokemonDetailsNavigate = (pokemonResult: PokemonResult, view: View, Int, String?) -> Unit