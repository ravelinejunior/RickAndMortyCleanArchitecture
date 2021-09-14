package br.com.raveline.appnew.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.raveline.appnew.data.db.dao.CharactersDao
import br.com.raveline.appnew.data.db.entity.CharacterEntity
import br.com.raveline.appnew.utils.CharacterTypeConverter

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CharacterTypeConverter::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
}