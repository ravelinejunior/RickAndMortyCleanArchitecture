package br.com.raveline.appnew.domain.usecases

import br.com.raveline.appnew.data.db.entity.CharacterEntity
import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CharactersUseCase(private val rickAndMortyRepository: RickAndMortyRepository) {
    suspend fun executeGetAllCharacters(): Response<Characters> {
        return rickAndMortyRepository.getAllCharacters()
    }

    suspend fun executeInsertCharacters(characterEntity: CharacterEntity) {
        rickAndMortyRepository.insertCharacters(characterEntity)
    }

     fun executeReadCharactersTable(): Flow<List<CharacterEntity>> {
        return rickAndMortyRepository.readCharactersTable()
    }
}