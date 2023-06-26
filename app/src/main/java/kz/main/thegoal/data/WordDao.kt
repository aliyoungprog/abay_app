package kz.main.thegoal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.main.thegoal.domain.entity.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getFavoritesWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(word: Word)

    @Delete
    fun deleteFromFavorites(word: Word)
}
