package com.ryutec.pokedexv1.data.model.pokemon

import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import com.ryutec.pokedexv1.data.model.detalles.Types

data class PokemonModel(
    val name:String,
    val url:String,
    val sprites:List<PokemonSprites>,
    val types:List<Types>
)
