package br.com.raveline.appnew.domain.repository

import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.util.Resource
import retrofit2.Response

interface RickAndMortyRepository {
    suspend fun getAllCharacters(): Response<Characters>
}