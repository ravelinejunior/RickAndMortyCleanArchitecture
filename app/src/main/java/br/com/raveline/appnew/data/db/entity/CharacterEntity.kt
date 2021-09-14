package br.com.raveline.appnew.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.raveline.appnew.data.model.Characters
import br.com.raveline.appnew.utils.Constants.CHARACTER_TABLE_NAME

@Entity(tableName = CHARACTER_TABLE_NAME)
class CharacterEntity(var characters: Characters) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}