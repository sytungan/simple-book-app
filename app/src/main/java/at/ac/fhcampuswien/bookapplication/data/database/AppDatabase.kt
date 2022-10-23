package at.ac.fhcampuswien.bookapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import at.ac.fhcampuswien.bookapplication.models.Book

@Database(entities = [Book::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val dataBaseName = "book_app"
        fun builder(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, dataBaseName
            ).build()
        }
    }

    abstract fun bookDao(): BookDao
}