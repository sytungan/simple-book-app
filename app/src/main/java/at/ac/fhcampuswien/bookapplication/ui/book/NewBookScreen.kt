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
import at.ac.fhcampuswien.bookapplication.viewmodels.NewBookViewModel
import at.ac.fhcampuswien.bookapplication.widgets.AppBar
import androidx.lifecycle.viewmodel.compose.*
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.utils.DateTimeUtils

@Composable
fun NewBookScreen(navHostController: NavHostController, viewModel: NewBookViewModel = viewModel()) {
    Scaffold(
        topBar = { AppBar(title = AppScreen.NewBook.title) }
    ) {
        Button(onClick = {
            viewModel.addBookToDB(Book(
                name = "Book sample",
                author = "Khanh Nguyen Dinh",
                date = DateTimeUtils.currentTimeStamp().toInt(),
                iBSN = "XXXXX"
            ))
        }) {
            Text(text = "Create a Book", color = AppColors.Purple200)
        }
    }
}

@Preview
@Composable
fun NewBookScreenPreview() {
    NewBookScreen(NavHostController(context = LocalContext.current))
}