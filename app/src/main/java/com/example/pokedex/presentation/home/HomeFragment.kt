package com.example.pokedex.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.presentation.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
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
}