package at.ac.fhcampuswien.bookapplication.ui.book

sealed class NewBookEvent {
    data class EnteredBookName(val bookName: String) : NewBookEvent()
    data class EnteredArthur(val arthur: String) : NewBookEvent()
    object EnteredDate : NewBookEvent()
    data class EnteredDigit(val digit: String) : NewBookEvent()

}