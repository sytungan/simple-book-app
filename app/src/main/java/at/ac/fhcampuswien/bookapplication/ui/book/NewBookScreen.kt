package at.ac.fhcampuswien.bookapplication.ui.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.utils.DateTimeUtils
import at.ac.fhcampuswien.bookapplication.widgets.CustomOutlineTextField

@Composable
fun NewBookScreen(navHostController: NavHostController, viewModel: NewBookViewModel = viewModel()) {
    Scaffold(
        topBar = { AppBar(title = AppScreen.NewBook.title) }
    ) {
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
                viewModel.addBookToDB(
                    Book(
                        name = "Book sample",
                        author = "Khanh Nguyen Dinh",
                        date = DateTimeUtils.currentTimeStamp().toInt(),
                        iBSN = "XXXXX"
                    )
                )
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