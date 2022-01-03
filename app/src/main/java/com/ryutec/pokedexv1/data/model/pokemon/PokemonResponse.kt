package com.ryutec.pokedexv1.data.model.pokemon

import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import com.ryutec.pokedexv1.data.model.detalles.Types

data class PokemonResponse (
    var results:List<PokemonModel>,
    val sprites:PokemonSprites,
    val types:List<Types>,
    val name:String
)