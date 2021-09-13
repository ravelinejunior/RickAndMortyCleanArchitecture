package br.com.raveline.appnew.presentation.di

import br.com.raveline.appnew.domain.repository.RickAndMortyRepository
import br.com.raveline.appnew.domain.usecases.CharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCharacterUseCase(rickAndMortyRepository: RickAndMortyRepository):CharactersUseCase{
        return CharactersUseCase(rickAndMortyRepository)
    }
}