package com.example.pokedex.presentation.home

import com.example.pokedex.data.usecase.GetPokemonUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var pokemonViewModel: HomeViewModel

    @Mock
    lateinit var getPokemonUseCase: GetPokemonUseCase
    @Before
    fun setup() {
         pokemonViewModel = HomeViewModel(getPokemonUseCase)
    }
}