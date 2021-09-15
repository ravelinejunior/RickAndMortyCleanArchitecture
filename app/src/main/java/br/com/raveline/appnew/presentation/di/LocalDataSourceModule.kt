package br.com.raveline.appnew.presentation.di

import br.com.raveline.appnew.data.datasources.datasource.CharactersLocalDataSource
import br.com.raveline.appnew.data.datasources.datasource_impl.CharactersLocalDataSourceImpl
import br.com.raveline.appnew.data.db.dao.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        charactersDao: CharactersDao
    ):CharactersLocalDataSource{
        return CharactersLocalDataSourceImpl(charactersDao)
    }
}