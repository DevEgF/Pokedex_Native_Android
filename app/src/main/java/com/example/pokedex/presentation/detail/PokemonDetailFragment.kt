package com.example.pokedex.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding get() = _binding!!
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
        binding.pokemonImage.run {
            transitionName = pokemonResult.name.capitalize()
            Glide.with(context)
                .load(picture)
                .fallback(R.drawable.ic_img_loading_error)
                .into(this)
        }
        setSharedElementTransitionOnEnter()

        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            val logResult = when(uiState) {
                PokemonDetailViewModel.UiState.Loading -> "Loading stats.."
                is PokemonDetailViewModel.UiState.Success -> uiState.singlePokemonResponse.toString()
                PokemonDetailViewModel.UiState.Error -> "Error when loading"
            }

            Log.d(PokemonDetailFragment::class.simpleName, logResult)
        }
        viewModel.singlePokemon(args.pokemonResult.url)
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

//    companion object {
//        private const val DEFAULT = 10.0
//    }
}