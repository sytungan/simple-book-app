package at.ac.fhcampuswien.bookapplication.ui.book

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors

@Composable
fun BookScreen(navHostController: NavHostController) {
    Button(onClick = {foo()}) {
        Text(text = "See friends list", color = AppColors.Purple200)
    }
}

fun foo() {
    println("haha")
}