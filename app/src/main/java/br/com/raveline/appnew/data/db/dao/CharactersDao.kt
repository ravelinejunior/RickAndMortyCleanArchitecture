package br.com.raveline.appnew.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.raveline.appnew.data.db.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterEntity: CharacterEntity)

    @Query("SELECT * FROM CHARACTER_TBL")
    fun readCharacters(): Flow<List<CharacterEntity>>
}