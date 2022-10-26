package at.ac.fhcampuswien.bookapplication.ui.book

import java.time.Instant
import java.util.*

data class DateFieldState(
    val date: Date? = null,
    val isError: Boolean = false,
    val error: String = "",
)
