package at.ac.fhcampuswien.bookapplication

import android.content.Context
import at.ac.fhcampuswien.bookapplication.data.database.AppDatabase

/**
 * A very simple global singleton dependency graph.
 *
 * For a real app, you would use something like Hilt/Dagger instead.
 */
object Graph {
    lateinit var database: AppDatabase
        private set
    fun provide(context: Context) {
        database = AppDatabase.builder(context)
    }
}