package at.ac.fhcampuswien.bookapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import at.ac.fhcampuswien.bookapplication.ui.book.NewBookScreen
import at.ac.fhcampuswien.bookapplication.ui.favoriate.FavoriteScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppRoute.favorite,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AppRoute.favorite) {
            FavoriteScreen(navController = navController)
        }
        composable(route = AppRoute.newBook) {
            NewBookScreen(navHostController = navController)
        }
    }
}
