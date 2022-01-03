package com.ryutec.pokedexv1.dominio

import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import javax.inject.Inject

class GetDetallesUseCase @Inject constructor(private val repository: Repository) {
    // recuperar los detalles
    suspend operator fun invoke(url: String): PokemonResponse {
        return repository.getAllPokemons(url)
    }
}