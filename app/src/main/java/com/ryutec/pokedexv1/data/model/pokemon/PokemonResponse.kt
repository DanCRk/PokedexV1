package com.ryutec.pokedexv1.data.model.pokemon

import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites

data class PokemonResponse (
    var results:List<PokemonModel>,
    var sprites: PokemonSprites,
)