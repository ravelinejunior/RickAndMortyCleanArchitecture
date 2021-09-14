package br.com.raveline.appnew.data.datasources.datasource_impl

import br.com.raveline.appnew.data.datasources.datasource.CharactersLocalDataSource
import br.com.raveline.appnew.data.db.dao.CharactersDao
import br.com.raveline.appnew.data.db.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

class CharactersLocalDataSourceImpl(private val charactersDao: CharactersDao) :
    CharactersLocalDataSource {
    override suspend fun insertCharacters(charactersEntity: CharacterEntity) {
        charactersDao.insertCharacters(charactersEntity)
    }

    override fun readCharactersTable(): Flow<List<CharacterEntity>> = charactersDao.readCharacters()
}