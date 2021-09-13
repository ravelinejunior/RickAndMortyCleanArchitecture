package br.com.raveline.appnew.presentation.di

import br.com.raveline.appnew.data.datasources.datasource.CharactersDataSource
import br.com.raveline.appnew.domain.repository.RickAndMortyRepository
import br.com.raveline.appnew.domain.repository.RickAndMortyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RickAndMortyRemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideRandMRemoteDataSource(rickAndMortyDataSource: CharactersDataSource): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(rickAndMortyDataSource)
    }
}