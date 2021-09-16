package br.com.raveline.appnew.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import br.com.raveline.appnew.data.db.entity.CharacterEntity
import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.domain.usecases.CharactersUseCase
import br.com.raveline.appnew.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(
    private val charactersUseCase: CharactersUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val charactersMutableLiveData: MutableLiveData<Resource<Characters>> = MutableLiveData()
    val charactersLiveData: LiveData<Resource<Characters>> = charactersMutableLiveData

    /*ROOM ELEMENTS*/
    val charactersLocalLiveData: LiveData<List<CharacterEntity>> =
        charactersUseCase.executeReadCharactersTable().asLiveData()

    fun insertCharacters(characterEntity: CharacterEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            charactersUseCase.executeInsertCharacters(characterEntity)
        }

    private fun offlineCacheCharacters(characters: Characters) {
        //create a entity
        val characterEntity = CharacterEntity(characters)
        insertCharacters(characterEntity)
    }

    fun getAllCharacters() = viewModelScope.launch {
        getAllCharactersSafeCall()
    }

    private suspend fun getAllCharactersSafeCall() {
        charactersMutableLiveData.postValue(Resource.Loading())

        if (isNetworkAvailable(app)) {
            try {
                val response = charactersUseCase.executeGetAllCharacters()

                charactersMutableLiveData.postValue(handleCharactersResponse(response))

                charactersMutableLiveData.value = handleCharactersResponse(response)

                //TODO("CRIAR FUNÇÃO PARA SALVAR PRIMEIRA REQUISIÇÃO NO BANCO DE DADOS")
                val characters = charactersLiveData.value!!.data
                if(characters != null){
                    offlineCacheCharacters(characters)
                }
            } catch (e: Exception) {
                charactersMutableLiveData.postValue(Resource.Error(e.message))
            }

        } else {
            charactersMutableLiveData.postValue(Resource.Error("No Internet Connection"))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }

                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }

        return false
    }

    private fun handleCharactersResponse(response: Response<Characters>): Resource<Characters> {
        when {
            response.message().toString().contains("timeout") -> {
                return Resource.Error("Timeout")
            }

            response.code() == 402 -> {
                return Resource.Error("API Key Limited")
            }

            response.body()!!.results.isNullOrEmpty() -> {

                return Resource.Error("Recipes not Found")
            }

            response.isSuccessful -> {
                val characters = response.body()
                return Resource.Success(characters!!)
            }

            else -> {
                return Resource.Error(response.message())
            }
        }
    }
}