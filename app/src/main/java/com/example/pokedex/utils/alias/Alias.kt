package com.example.pokedex.utils.alias

import android.view.View
import com.example.pokedex.domain.model.PokemonResult

typealias PokemonDetailsNavigate = (pokemonResult: PokemonResult, view: View, Int, String?) -> Unit