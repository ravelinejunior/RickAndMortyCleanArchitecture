package br.com.raveline.appnew.presentation.di

import android.app.Application
import br.com.raveline.appnew.domain.usecases.CharactersUseCase
import br.com.raveline.appnew.presentation.viewmodels.CharactersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideCharacterViewModelFactory(
        charactersUseCase: CharactersUseCase,
        application: Application
    ): CharactersViewModelFactory {
        return CharactersViewModelFactory(charactersUseCase, application)
    }
}