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
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookState
import kotlinx.coroutines.launch

class NewBookViewModel(
    private val db : AppDatabase = Graph.database
) : ViewModel() {
    var uiState by mutableStateOf(NewBookState())
        private set

    fun addBookToDB(book: Book) {
        uiState = uiState.copy(
            loading = true
        )
        viewModelScope.launch {
            db.withTransaction {
                val dao = db.bookDao()
                dao.insertAll(book)
            }
        }
        uiState = uiState.copy(
            loading = false
        )
    }

    // TODO: Create some functions to validate the logic, they are:
    /*
    - Name can not be empty
    - Author: must not be empty
    - First publication date: must be a valid year and cannot be in the future
    */
}