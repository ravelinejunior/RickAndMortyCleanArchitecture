package br.com.raveline.appnew.data.datasources.datasource

import br.com.raveline.appnew.data.model.Characters
import retrofit2.Response

interface CharactersDataSource {

    suspend fun getAllCharacters():Response<Characters>
}