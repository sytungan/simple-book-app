package at.ac.fhcampuswien.bookapplication.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import at.ac.fhcampuswien.bookapplication.Graph
import at.ac.fhcampuswien.bookapplication.data.database.AppDatabase
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.ui.favoriate.FavoriteState
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val db : AppDatabase = Graph.database
) : ViewModel() {
    var uiState by mutableStateOf(FavoriteState())
        private set

    init {
        getAllBookFromDB()
    }

    fun getAllBookFromDB() {
        // Set state to loading
        uiState = uiState.copy(
            loading = true
        )

        // Launch database access works in another scope
        viewModelScope.launch {
            val dao = db.bookDao()
            db.withTransaction {
                uiState = uiState.copy(
                    books = dao.getAll(),
                    loading = false
                )
            }
        }
    }

    fun deleteBookFromDB(book: Book) {
        // Set state to loading
        uiState = uiState.copy(
            loading = true
        )

        // Launch database access works in another scope
        viewModelScope.launch {
            db.withTransaction {
                val dao = db.bookDao()
                dao.delete(book)
                uiState = uiState.copy(
                    loading = true
                )
            }
            // After delete a book -> Reload all
            getAllBookFromDB()
        }
    }
}