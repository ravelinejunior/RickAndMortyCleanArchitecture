package br.com.raveline.appnew.utils

import androidx.room.TypeConverter
import br.com.raveline.appnew.data.model.Character
import br.com.raveline.appnew.data.model.Characters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterTypeConverter {

    var gson = Gson()

    //converte para string
    @TypeConverter
    fun charactersToString(characters: Characters): String {
        return gson.toJson(characters)
    }

    //converte string para characters
    @TypeConverter
    fun stringToCharacters(characterString: String): Characters {
        val listType = object : TypeToken<Characters>() {}.type
        return gson.fromJson(characterString, listType)
    }

    /*CONVERTER UM UNICO OBJETO Character PARA STRING E VICE-VERSA*/
    @TypeConverter
    fun characterToString(character: Character): String {
        return gson.toJson(character)
    }

    @TypeConverter
    fun stringToCharacterSingleObject(value: String): Character {
        val listType = object : TypeToken<Character>() {}.type
        return gson.fromJson(value, listType)
    }
}