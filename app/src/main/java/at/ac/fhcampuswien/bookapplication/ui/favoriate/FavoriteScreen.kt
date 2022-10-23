package at.ac.fhcampuswien.bookapplication.ui.favoriate

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.navigation.AppRoute

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Button(onClick = {
        navController.navigate(AppRoute.favorite)
    }) {
        Text(text = "Home haha")
    }
}