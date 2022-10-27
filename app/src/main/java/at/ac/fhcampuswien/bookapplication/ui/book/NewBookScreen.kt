package at.ac.fhcampuswien.bookapplication.ui.book

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.navigation.AppScreen
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.viewmodels.NewBookViewModel
import at.ac.fhcampuswien.bookapplication.widgets.AppBar
import androidx.lifecycle.viewmodel.compose.*
import at.ac.fhcampuswien.bookapplication.BookApp
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography
import at.ac.fhcampuswien.bookapplication.utils.DateTimeUtils
import at.ac.fhcampuswien.bookapplication.widgets.CustomOutlineTextField
import at.ac.fhcampuswien.bookapplication.widgets.DateTimeTextField

@Composable
fun NewBookScreen(navHostController: NavHostController, viewModel: NewBookViewModel = viewModel()) {
    val uiState = viewModel.uiState
    Scaffold(
        topBar = { AppBar(title = AppScreen.NewBook.title) }
    ) {
        if (uiState.isCreated) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .height(400.dp)
            ) {
                Text(text = "Create a book successfully, try to reload at My Favorite Books", style = AppTypography.h5)
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            viewModel.nameState.value.run {
                CustomOutlineTextField(
                    text = text,
                    onValueChange = { text -> viewModel.emitEvent(NewBookEvent.EnteredBookName(text)) },
                    label = "Name",
                    hint = "Name",
                    isError = isError,
                    error = error,
                )
            }
            viewModel.authorState.value.run {
                CustomOutlineTextField(
                    text = text,
                    onValueChange = { text -> viewModel.emitEvent(NewBookEvent.EnteredAuthor(text)) },
                    label = "Author",
                    hint = "Author",
                    isError = isError,
                    error = error,
                )
            }
            viewModel.dateState.value.run {
                DateTimeTextField(
                    label = "First publication date",
                    hint = "Choose the first publication date",
                    isError = isError,
                    error = error,
                    onValueChange = { date ->
                        viewModel.emitEvent(NewBookEvent.EnteredDate(date))
                    }
                )
            }
            viewModel.iBSNState.value.run {
                CustomOutlineTextField(
                    text = text,
                    onValueChange = { text -> viewModel.emitEvent(NewBookEvent.EnteredISBN(text)) },
                    label = "ISBN",
                    hint = "ISBN",
                    isError = isError,
                    error = error,
                )
            }
            Button(onClick = {
                viewModel.emitEvent(NewBookEvent.SubmitBook)
            }) {
                Text(text = "Create a Book", color = AppColors.Purple200)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NewBookScreenPreview() {
    NewBookScreen(NavHostController(context = LocalContext.current))
}