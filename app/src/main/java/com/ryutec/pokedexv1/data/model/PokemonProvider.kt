package com.ryutec.pokedexv1.data.model

import com.ryutec.pokedexv1.data.model.detalles.PokemonDetails
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonProvider @Inject constructor() {
    lateinit var pokemons: PokemonResponse
    lateinit var details:PokemonDetails
}