package at.ac.fhcampuswien.bookapplication.ui.book

import at.ac.fhcampuswien.bookapplication.models.Book

data class NewBookState(
    val isCreated: Boolean = false,
    val loading: Boolean = false
)