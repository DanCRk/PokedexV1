package com.ryutec.pokedexv1.dominio

import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import com.ryutec.pokedexv1.data.model.detalles.Type
import com.ryutec.pokedexv1.data.model.detalles.Types
import javax.inject.Inject

class GetTypesUseCase @Inject constructor(private val repository: Repository) {
    // recuperar los detalles
    suspend operator fun invoke(url: String): List<Types> {
        return repository.getDetails(url).types
    }
}