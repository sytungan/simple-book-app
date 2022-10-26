package at.ac.fhcampuswien.bookapplication.ui.book

import java.util.*

sealed class NewBookEvent {
    data class EnteredBookName(val name: String) : NewBookEvent()
    data class EnteredAuthor(val author: String) : NewBookEvent()
    data class EnteredDate(val date: Date) : NewBookEvent()
    data class EnteredISBN(val iSBN: String) : NewBookEvent()
    object SubmitBook : NewBookEvent()
}