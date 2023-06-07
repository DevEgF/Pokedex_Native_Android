package com.example.pokedex.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.presentation.detail.adapter.PokemonDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding get() = _binding!!
    private val args by navArgs<PokemonDetailFragmentArgs>()
    private lateinit var pokemonDetailsAdapter: PokemonDetailAdapter
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
        setupPokemonImage()
        setSharedElementTransitionOnEnter()
        setupViewObserver()
    }

    private fun setupViewObserver(){
        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            when(uiState) {
                PokemonDetailViewModel.UiState.Loading -> {
                    "Loading stats.."
                }
                is PokemonDetailViewModel.UiState.Success -> {

                    val statsList = uiState.singlePokemonResponse.stats
                    val statsArrayList = ArrayList(statsList)
                    val height = (uiState.singlePokemonResponse.height.div(DEFAULT).toString() + " mts")
                    val weight = (uiState.singlePokemonResponse.weight.div(DEFAULT).toString() + " kgs")
                    val id = uiState.singlePokemonResponse.id.toString()

                    binding.pokemonItemHeight.text = height
                    binding.pokemonItemWeight.text = weight
                    binding.pokedexOrder.text = id

                    pokemonDetailsAdapter = PokemonDetailAdapter()
                    binding.pokemonStatList.run {
                        setHasFixedSize(true)
                        adapter = pokemonDetailsAdapter
                        pokemonDetailsAdapter.setStats(statsArrayList)
                    }
                }
                PokemonDetailViewModel.UiState.Error -> {


                }
            }
        }
        viewModel.singlePokemon(args.pokemonResult.url)
    }

    private fun setupPokemonImage(){
        val pokemonResult = args.pokemonResult
        val picture = args.picture

        binding.pokemonItemImage.run {
            transitionName = pokemonResult.name.capitalize()
            Glide.with(context)
                .load(picture)
                .fallback(R.drawable.ic_img_loading_error)
                .into(this)
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val DEFAULT = 10.0
    }
}