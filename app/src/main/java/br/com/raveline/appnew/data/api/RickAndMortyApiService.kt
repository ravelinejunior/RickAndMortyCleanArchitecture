package br.com.raveline.appnew.data.api

import br.com.raveline.appnew.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    suspend fun requestAllCharacters():Response<Characters>
}