package at.ac.fhcampuswien.bookapplication.ui.book

data class TextFieldState(
    val text: String = "",
    val isError: Boolean = false,
    val error: String = "",
)
