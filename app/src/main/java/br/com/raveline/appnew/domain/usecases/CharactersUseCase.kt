package br.com.raveline.appnew.domain.usecases

import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.repository.RickAndMortyRepository
import br.com.raveline.appnew.domain.util.Resource
import retrofit2.Response

class CharactersUseCase(private val rickAndMortyRepository: RickAndMortyRepository) {
    suspend fun executeGetAllCharacters(): Response<Characters> {
        return rickAndMortyRepository.getAllCharacters()
    }
}