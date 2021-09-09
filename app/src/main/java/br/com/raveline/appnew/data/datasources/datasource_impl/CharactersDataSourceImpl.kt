package br.com.raveline.appnew.data.datasources.datasource_impl

import br.com.raveline.appnew.data.api.RickAndMortyApiService
import br.com.raveline.appnew.data.datasources.datasource.CharactersDataSource
import br.com.raveline.appnew.data.model.Characters
import retrofit2.Response

class CharactersDataSourceImpl(
    private val rickAndMortyApiService: RickAndMortyApiService
) : CharactersDataSource {
    override suspend fun getAllCharacters(): Response<Characters> {
        return rickAndMortyApiService.requestAllCharacters()
    }
}