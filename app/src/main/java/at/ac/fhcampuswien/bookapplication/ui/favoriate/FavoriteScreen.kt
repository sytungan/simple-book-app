package at.ac.fhcampuswien.bookapplication.ui.favoriate

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.navigation.AppRoute
import at.ac.fhcampuswien.bookapplication.navigation.AppScreen
import at.ac.fhcampuswien.bookapplication.widgets.AppBar

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppBar(title = AppScreen.Favorite.title) }
    ) {
        Button(onClick = {
            navController.navigate(AppRoute.favorite)
        }) {
            Text(text = "Home haha")
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(NavHostController(context = LocalContext.current))
}