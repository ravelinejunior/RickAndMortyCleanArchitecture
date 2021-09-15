package br.com.raveline.appnew.presentation.di

import android.app.Application
import androidx.room.Room
import br.com.raveline.appnew.data.db.CharacterDatabase
import br.com.raveline.appnew.data.db.dao.CharactersDao
import br.com.raveline.appnew.utils.Constants.CHARACTER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CharacterDatabase {
        return Room.databaseBuilder(application, CharacterDatabase::class.java, CHARACTER_DATABASE)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: CharacterDatabase): CharactersDao {
        return database.characterDao()
    }

}