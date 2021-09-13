package br.com.raveline.appnew.domain.repository

import br.com.raveline.appnew.data.datasources.datasource.CharactersDataSource
import br.com.raveline.appnew.data.model.Characters
import retrofit2.Response

class RickAndMortyRepositoryImpl(private val charactersDataSource: CharactersDataSource):RickAndMortyRepository {
    override suspend fun getAllCharacters(): Response<Characters> {
        return charactersDataSource.getAllCharacters()
    }
}