package br.com.raveline.appnew.data.datasources.datasource

import br.com.raveline.appnew.data.db.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    suspend fun insertCharacters(charactersEntity: CharacterEntity)
    fun readCharactersTable():Flow<List<CharacterEntity>>
}