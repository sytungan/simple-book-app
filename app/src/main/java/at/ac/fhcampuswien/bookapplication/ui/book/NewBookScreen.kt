package at.ac.fhcampuswien.bookapplication.ui.book

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.navigation.AppScreen
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.widgets.AppBar

@Composable
fun BookScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { AppBar(title = AppScreen.Favorite.title) }
    ) {
        Button(onClick = {}) {
            Text(text = "See friends list", color = AppColors.Purple200)
        }
    }
}

@Preview
@Composable
fun BookScreenPreview() {
    BookScreen(NavHostController(context = LocalContext.current))
}