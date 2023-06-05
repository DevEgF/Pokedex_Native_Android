package com.example.pokedex.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.network.domain.Stats
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.presentation.detail.adapter.PokemonDetailAdapter
import com.example.pokedex.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding get() = _binding!!
    private val pokemonDetailsAdapter = PokemonDetailAdapter()
    private val args by navArgs<PokemonDetailFragmentArgs>()
    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonResult = args.pokemonResult
        val picture = args.picture

        with(binding){
            Glide.with(root)
                .load(picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(pokemonItemImage)
        }

        loadSinglePokemon(pokemonResult)
    }

    private fun loadSinglePokemon(pokemonResult: PokemonResult){
        lifecycleScope.launch (Dispatchers.Main){

            viewModel.getSinglePokemon(pokemonResult.url).collect {
                when (it) {
                    is NetworkResource.Success -> {
                        with(binding){
                            (it.value.weight.div(10.0).toString() + " kgs").also { weight ->
                                pokemonItemWeight.text = weight
                            }
                            (it.value.height.div(10.0).toString() + " metres").also { height ->
                                pokemonItemHeight.text = height
                            }
                            pokemonStatList.adapter = pokemonDetailsAdapter
                            pokemonDetailsAdapter.setStats(it.value.stats as ArrayList<Stats>)
                        }
                    }
                    is NetworkResource.Failure -> {

                    }

                    is NetworkResource.Loading -> {

                    }
                }
            }
        }
    }
}