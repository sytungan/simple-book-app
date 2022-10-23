package at.ac.fhcampuswien.bookapplication.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable

object AppRoute {
    const val favorite = "favorite"
    const val newBook = "book"
    const val bookDetail = "book"
}

sealed class AppScreen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Favorite : AppScreen(
        AppRoute.favorite,
        "My Favorite Books",
        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
    )

    object NewBook : AppScreen(
        AppRoute.newBook,
        "Create/Edit New Book",
        icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
    )
}