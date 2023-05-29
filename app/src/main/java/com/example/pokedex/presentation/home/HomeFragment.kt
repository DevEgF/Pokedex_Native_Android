package com.example.pokedex.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.presentation.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var job: Job? = null

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
        startFetchPokemon("", true)
        observeInitialLoadState()
    }

    private fun initPokemonAdapter() {
        with(binding.recyclerPokemon){
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
    }

    private fun startFetchPokemon(queries: String, shouldSubmitEmpty: Boolean) {
        job?.cancel()
        job = lifecycleScope.launch {
            if(shouldSubmitEmpty) pokemonAdapter.submitData(PagingData.empty())
            viewModel.getPokemons(queries).collectLatest {
                pokemonAdapter.submitData(it)
            }
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            pokemonAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperPokemon.displayedChild = when(loadState.refresh){
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_POKEMON
                    }
                    is LoadState.Error -> FLIPPER_CHILD_ERROR
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewPokemonLoadingState.shimmerPokemon.run{
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_POKEMON = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}