package br.com.raveline.appnew.presentation.di

import br.com.raveline.appnew.data.api.RickAndMortyApiService
import br.com.raveline.appnew.data.datasources.datasource.CharactersDataSource
import br.com.raveline.appnew.data.datasources.datasource_impl.CharactersDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDataSource(
        rickAndMortyApiService: RickAndMortyApiService
    ): CharactersDataSource {
        return CharactersDataSourceImpl(rickAndMortyApiService)
    }

}