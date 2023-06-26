package kz.main.thegoal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.main.thegoal.domain.entity.Word

@Database(entities = [Word::class], version = 2, exportSchema = false)
abstract class WordsDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}
