package at.ac.fhcampuswien.movieapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import at.ac.fhcampuswien.movieapplication.ui.book.BookScreen
import at.ac.fhcampuswien.movieapplication.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppRoute.home,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AppRoute.home) {
            HomeScreen(navController = navController)
        }
        composable(route = AppRoute.book) {
            BookScreen(navHostController = navController)
        }
    }
}
