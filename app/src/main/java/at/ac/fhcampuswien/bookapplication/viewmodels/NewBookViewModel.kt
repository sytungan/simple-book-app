package at.ac.fhcampuswien.bookapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import at.ac.fhcampuswien.bookapplication.AppSingletons
import at.ac.fhcampuswien.bookapplication.data.database.AppDatabase
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookEvent
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookState
import at.ac.fhcampuswien.bookapplication.ui.book.TextFieldState
import kotlinx.coroutines.launch

class NewBookViewModel(
    private val db: AppDatabase = AppSingletons.database
) : ViewModel() {
    var uiState by mutableStateOf(NewBookState())
        private set

    val nameState = mutableStateOf(TextFieldState())
    val authorState = mutableStateOf(TextFieldState())
    val dateState = mutableStateOf(TextFieldState())
    val iBSNState = mutableStateOf(TextFieldState())

    fun emitEvent(event: NewBookEvent) {
        when (event) {
            is NewBookEvent.EnteredBookName -> {
                nameState.value = nameState.value.copy(text = event.name)
            }
            is NewBookEvent.EnteredAuthor -> {
                authorState.value = authorState.value.copy(text = event.author)
            }
            is NewBookEvent.EnteredDate -> {
                nameState.value = dateState.value.copy(text = event.date.toString())
            }
            is NewBookEvent.EnteredISBN -> {
                iBSNState.value = iBSNState.value.copy(text = event.iSBN)
            }
        }
    }

    fun validateISBN() {
        val bookName = nameState.value.text
        val iSBNPattern = """(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?\$)""".toRegex()
        if (bookName.isNotEmpty() && iSBNPattern.matches(bookName)) {
            nameState.value = nameState.value.copy(isError = false);
        }
        else {
            nameState.value = nameState.value.copy(isError = true);
        }
    }

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
    /**
     * - Name can not be empty
     * - Author: must not be empty
     * - First publication date: must be a valid year and cannot be in the future
     */
}