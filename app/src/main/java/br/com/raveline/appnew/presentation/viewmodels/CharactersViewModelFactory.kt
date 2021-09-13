package br.com.raveline.appnew.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.raveline.appnew.domain.usecases.CharactersUseCase

class CharactersViewModelFactory(
    private val charactersUseCase: CharactersUseCase,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(charactersUseCase, application) as T
    }

}