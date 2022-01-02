package com.ryutec.pokedexv1.dominio

import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import javax.inject.Inject

class GetSpritesUseCase @Inject constructor(private val repository: Repository) {
    // recuperar los detalles
    suspend operator fun invoke(url: String): PokemonSprites {
        return repository.getAllPokemons(url).sprites
    }
}