package at.ac.fhcampuswien.bookapplication

import android.content.Context
import at.ac.fhcampuswien.bookapplication.data.database.AppDatabase

/**
 * A very simple global singleton dependency object.
 *
 * For a real app, you would use something like Hilt/Dagger instead.
 */
object AppSingletons {
    lateinit var database: AppDatabase
        private set
    fun provide(context: Context) {
        database = AppDatabase.builder(context)
    }
}