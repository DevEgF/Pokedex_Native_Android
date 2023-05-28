package com.example.pokedex.presentation.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.R

class FavoritesPokemonsFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesPokemonsFragment()
    }

    private lateinit var viewModel: FavoritesPokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_pokemons, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesPokemonsViewModel::class.java)
        //Use the ViewModel
    }

}