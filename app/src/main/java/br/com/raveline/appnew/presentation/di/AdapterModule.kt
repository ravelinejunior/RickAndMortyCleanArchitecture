package br.com.raveline.appnew.presentation.di

import android.app.Application
import br.com.raveline.appnew.presentation.adapter.CharactersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideCharactersAdapter(
        context: Application
    ): CharactersAdapter = CharactersAdapter(context)
}