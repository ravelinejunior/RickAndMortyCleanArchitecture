package br.com.raveline.appnew.domain.usecases

import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.repository.RickAndMortyRepository
import br.com.raveline.appnew.domain.util.Resource

class CharactersUseCase(private val rickAndMortyRepository: RickAndMortyRepository) {
    suspend fun executeGetAllCharacters(): Resource<Characters> {
        return rickAndMortyRepository.getAllCharacters()
    }
}