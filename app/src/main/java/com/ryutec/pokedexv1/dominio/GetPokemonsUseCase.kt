package com.ryutec.pokedexv1.dominio

import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: Repository) {
    // recuperar los pokemons
    suspend operator fun invoke(url: String): List<PokemonModel> {
        return repository.getAllPokemons(url).results
    }
}