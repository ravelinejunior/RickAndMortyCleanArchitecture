package br.com.raveline.appnew.domain.repository

import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.util.Resource

interface RickAndMortyRepository {
    suspend fun getAllCharacters():Resource<Characters>
}