package br.com.raveline.appnew.domain.repository

import br.com.raveline.appnew.data.datasources.datasource.CharactersDataSource
import br.com.raveline.appnew.data.datasources.datasource.CharactersLocalDataSource
import br.com.raveline.appnew.data.db.entity.CharacterEntity
import br.com.raveline.appnew.data.model.Characters
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RickAndMortyRepositoryImpl(
    private val charactersDataSource: CharactersDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource
) : RickAndMortyRepository {
    override suspend fun getAllCharacters(): Response<Characters> {
        return charactersDataSource.getAllCharacters()
    }

    override suspend fun insertCharacters(characterEntity: CharacterEntity) {
        charactersLocalDataSource.insertCharacters(characterEntity)
    }

    override fun readCharactersTable(): Flow<List<CharacterEntity>> {
        return charactersLocalDataSource.readCharactersTable()
    }
}