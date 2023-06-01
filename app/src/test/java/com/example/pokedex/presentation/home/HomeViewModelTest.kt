package com.example.pokedex.presentation.home

import com.example.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
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
    lateinit var pokemonRepository: PokemonRepository
    @Before
    fun setup() {
         pokemonViewModel = HomeViewModel(pokemonRepository)
    }
    @Test
    fun `should validate the paging data object values when calling PokemonPagingSource`() = runBlocking {
        val repository = pokemonRepository
        val queries = "pikachu"

        val result = repository.getPokemon(queries)

        assertNotNull(result)
    }
}