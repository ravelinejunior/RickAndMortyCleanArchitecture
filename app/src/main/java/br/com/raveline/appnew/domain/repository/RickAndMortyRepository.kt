package br.com.raveline.appnew.domain.repository

import br.com.raveline.appnew.data.db.entity.CharacterEntity
import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RickAndMortyRepository {
    suspend fun getAllCharacters(): Response<Characters>

    /*DATABASE*/
    suspend fun insertCharacters(characterEntity: CharacterEntity)
    fun readCharactersTable():Flow<List<CharacterEntity>>
}