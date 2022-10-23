package at.ac.fhcampuswien.bookapplication.ui.favoriate

import at.ac.fhcampuswien.bookapplication.models.Book

data class FavoriteState(
    var books: List<Book> = emptyList(),
    var loading: Boolean = false
)