package com.example.pokedex.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.presentation.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val pokemonAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPokemonAdapter()

        pokemonAdapter.submitList(
            listOf(
                PokemonResult(
                    "bulbasaur",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
                PokemonResult(
                    "ivysaur",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"),
                PokemonResult(
                    "venusaur",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"),
                PokemonResult(
                    "charmander",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"),
                PokemonResult(
                    "charmeleon",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"),
                PokemonResult(
                    "charizard",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"),
                PokemonResult(
                    "squirtle",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"),
                PokemonResult(
                    "wartortle",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png"),
                PokemonResult(
                    "blastoise",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png")
            )
        )
    }

    private fun initPokemonAdapter() {
        with(binding.recyclerPokemon){
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
    }
}