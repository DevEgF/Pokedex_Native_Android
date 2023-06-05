package com.example.pokedex.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex.data.usecase.base.ResultStatus
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.presentation.detail.adapter.PokemonDetailAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding get() = _binding!!
    private lateinit var pokemonDetailsAdapter: PokemonDetailAdapter
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
        initSinglePokemonAdapter()
        val pokemonResult = args.pokemonResult
        val picture = args.picture

        with(binding){
            Glide.with(root)
                .load(picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(pokemonItemImage)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.singlePokemon(pokemonResult.url).collect{ resultStatus ->
                    when(resultStatus) {
                        is ResultStatus.Loading -> {

                        }
                        is ResultStatus.Success -> {
                            val pokemonData = resultStatus.data
                            val statsList = pokemonData.stats
                            val statsArrayList = ArrayList(statsList)
                            val height = (pokemonData.height.div(DEFAULT).toString() + " metres")
                            val weight = (pokemonData.weight.div(DEFAULT).toString() + " kgs")
                            pokemonDetailsAdapter.setStats(statsArrayList)

                            with(binding){
                                pokemonItemHeight.text = height
                                pokemonItemWeight.text = weight
                            }
                        }
                        is ResultStatus.Error -> {

                        }
                    }
                }
            }
        }
    }

    private fun initSinglePokemonAdapter() {
        pokemonDetailsAdapter = PokemonDetailAdapter()
        with(binding.pokemonStatList){
            setHasFixedSize(true)
            adapter = pokemonDetailsAdapter
        }
    }

    companion object {
        private const val DEFAULT = 10.0
    }
}