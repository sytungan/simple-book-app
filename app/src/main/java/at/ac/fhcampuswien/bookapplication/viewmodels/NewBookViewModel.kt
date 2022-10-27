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
import at.ac.fhcampuswien.bookapplication.ui.book.DateFieldState
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookEvent
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookState
import at.ac.fhcampuswien.bookapplication.ui.book.TextFieldState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class NewBookViewModel(
    private val db: AppDatabase = AppSingletons.database
) : ViewModel() {
    var uiState by mutableStateOf(NewBookState())
        private set

    val nameState = mutableStateOf(TextFieldState())
    val authorState = mutableStateOf(TextFieldState())
    val dateState = mutableStateOf(DateFieldState())
    val iBSNState = mutableStateOf(TextFieldState())

    fun emitEvent(event: NewBookEvent) {
        when (event) {
            is NewBookEvent.EnteredBookName -> {
                nameState.value = nameState.value.copy(text = event.name, isError = false)
            }
            is NewBookEvent.EnteredAuthor -> {
                authorState.value = authorState.value.copy(text = event.author, isError = false)
            }
            is NewBookEvent.EnteredDate -> {
                dateState.value = dateState.value.copy(date = event.date, isError = false)
            }
            is NewBookEvent.EnteredISBN -> {
                iBSNState.value = iBSNState.value.copy(text = event.iSBN, isError = false)
            }
            is NewBookEvent.SubmitBook -> {
                submitBook()
            }
        }
    }

    private fun validateISBN13(isbnNum: String, checkDigit: String): String {
        var sum = 0
        try {
            for (i in 0..isbnNum.length - 2) {
                sum += (i % 2 * 2 + 1) * isbnNum[i].digitToInt()
            }
        }
        catch (e: Exception) {
            return "There is a group have nothing"
        }
        val check = 10 - (sum % 10)
        return if (check == checkDigit.toInt()) "" else "Invalid ISBN check digit"
    }

    private fun checkPrefix(prefix: String): String {
        val prefixRegex = "^97[89]"
        val pattern = Pattern.compile(prefixRegex)
        val matcher = pattern.matcher(prefix)
        return if (matcher.matches()) "" else "13-digit ISBNs must start with the prefix 978 or 979"
    }

    private fun checkGroupIdentifier(registrant: String): String {
        val groupLength = registrant.length
        return if (groupLength in 1..5) "" else "Group Identifier must ranges from one to five digits long"
    }

    private fun checkCheckDigit(checkDigit: String): String {
        checkDigit.toIntOrNull()?.let {
            if (it in 1..10) return ""
        }
        return "ISBN-13 check digit ranges from 0 to 9"
    }


    private fun submitBook() {
        val validName = validateName()
        val validAuthor = validateAuthor()
        var validISBN = false
        try {
            validISBN = validateISBN()
        }
        catch (e: Exception) {
            iBSNState.value = iBSNState.value.copy(isError = true, error = "Invalid ISBN")
        }
        val validDate = validateDate()
        if (  validName && validAuthor && validDate && validISBN
        ) {
            addBookToDB(
                Book(
                    name = nameState.value.text,
                    author = authorState.value.text,
                    date = dateState.value.date!!.toInstant().epochSecond.toInt(),
                    iBSN = iBSNState.value.text,
                )
            )
        }
    }

    private fun validateDate(): Boolean {
        return if (dateState.value.date != null) {
            dateState.value = dateState.value.copy(isError = false, error = "")
            true
        }
        else {
            dateState.value = dateState.value.copy(isError = true, error = "You have to choose the first publication date")
            false
        }
    }

    private fun validateISBN(): Boolean {
        val iSBNPattern = """^97[89][-]?[0-9]{1,5}[-]?[0-9]+[-]?[0-9]+[-]?[0-9]${'$'}"""
        val isbn = iBSNState.value.text
        val isbnNum = isbn.replace("-", "")
        val pattern = Pattern.compile(iSBNPattern)
        val matcher = pattern.matcher(isbn)
        val isbnGroup = isbn.split("-")
        if (isbnGroup.size <= 4) {
            iBSNState.value = iBSNState.value.copy(isError = true, error = "ISBN must be have at least 13 characters and separators")
            return false
        }
        val prefix = isbnGroup[0]
        val registrant = isbnGroup[2]
        val checkDigit = isbnGroup[4]
        if (!matcher.matches()) {
            val errorISBN13 = validateISBN13(isbnNum, checkDigit)
            if (errorISBN13.isNotEmpty()) {
                iBSNState.value = iBSNState.value.copy(isError = true, error = errorISBN13)
                return false
            }
            val errorPrefix = checkPrefix(prefix)
            if (errorPrefix.isNotEmpty()) {
                iBSNState.value = iBSNState.value.copy(isError = true, error = errorPrefix)
                return false
            }
            val errorGroup = checkGroupIdentifier(registrant)
            if (errorGroup.isNotEmpty()) {
                iBSNState.value = iBSNState.value.copy(isError = true, error = errorGroup)
                return false
            }
            val errorDigit = checkCheckDigit(checkDigit)
            if (errorDigit.isNotEmpty()) {
                iBSNState.value = iBSNState.value.copy(isError = true, error = errorDigit)
                return false
            }
        }
        return true;
    }

    private fun validateName(): Boolean {
        return if (nameState.value.text.isNotEmpty()) {
            nameState.value = nameState.value.copy(isError = false)
            true
        } else {
            nameState.value = nameState.value.copy(isError = true, error = "Name can not be empty")
            false
        }
    }

    private fun validateAuthor(): Boolean {
        return if (authorState.value.text.isNotEmpty()) {
            authorState.value = authorState.value.copy(isError = false);
            true
        } else {
            authorState.value =
                authorState.value.copy(isError = true, error = "Author must not be empty")
            false
        }
    }

    private fun addBookToDB(book: Book) {
        uiState = uiState.copy(
            loading = true
        )
        viewModelScope.launch {
            db.withTransaction {
                uiState = try {
                    val dao = db.bookDao()
                    dao.insertAll(book)
                    uiState.copy(
                        isCreated = true
                    )
                } catch (e: Exception) {
                    uiState.copy(
                        isCreated = false
                    )
                }
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