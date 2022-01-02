package com.ryutec.pokedexv1.data.network

import com.ryutec.pokedexv1.data.model.detalles.PokemonDetails
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonAPIClient {
    @GET
    suspend fun getAllPokemon(@Url url:String):Response<PokemonResponse>

}