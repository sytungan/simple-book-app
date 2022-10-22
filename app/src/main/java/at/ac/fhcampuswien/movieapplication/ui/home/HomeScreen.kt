package at.ac.fhcampuswien.movieapplication.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.movieapplication.navigation.AppRoute
import at.ac.fhcampuswien.movieapplication.ui.theme.*

@Composable
fun HomeScreen(navController: NavHostController) {
    Button(onClick = {
        navController.navigate(AppRoute.book)
    }) {
        Text(text = "Home haha")
    }
}