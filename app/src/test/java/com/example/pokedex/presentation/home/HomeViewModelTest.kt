package com.example.pokedex.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokedex.domain.usecase.GetPokemonUseCase
import com.example.pokedex.feature.home.HomeViewModel
import com.example.pokedex.utils.MainCoroutineRule
import com.example.pokedex.utils.factory.PokemonFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getPokemonUseCase: GetPokemonUseCase

    private lateinit var pokemonViewModel: HomeViewModel

    private val pokemonFactory = PokemonFactory()

    private val pagingDataPokemon = PagingData.from(
        listOf(
            pokemonFactory.create(PokemonFactory.PokemonTest.Pokemon1),
            pokemonFactory.create(PokemonFactory.PokemonTest.Pokemon2)
        )
    )

    @Before
    fun setup() {
        pokemonViewModel = HomeViewModel(getPokemonUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling pokemonPagingData`() =
        runTest {
            whenever(getPokemonUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        pagingDataPokemon
                    )
                )

            val result = pokemonViewModel.pokemonPagingData("").first()

            val expectedPagingData = pagingDataPokemon

            result.map { pokemonResult ->
                expectedPagingData.map {
                    assertEquals(pokemonResult.name, pokemonResult.url)
                }
            }
        }
}