package at.ac.fhcampuswien.bookapplication.ui.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun NewBookScreen(navHostController: NavHostController, viewModel: NewBookViewModel = viewModel()) {
    val bookName by remember { viewModel.bookName }
    Scaffold(
        topBar = { AppBar(title = AppScreen.NewBook.title) }
    ) {
        Column() {
            CustomOutlineTextField(
                text = "",
                onValueChange = { text -> viewModel.emitEvent(NewBookEvent.EnteredBookName(text)) },
                label = { Text(text = "Name")},
                textStyle = TextStyle(color = Color.Blue),
                modifier = Modifier.padding(20.dp),
                isError = bookName.error
            ) {
                viewModel.validate()
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

@Composable
fun CustomOutlineTextField(
    text:String,
    onValueChange: (String) -> Unit,
    label: @Composable() (()-> Unit),
    textStyle: TextStyle,
    modifier: Modifier,
    isError: Boolean,
    keyboardActions: (() -> Unit)?
){
    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            label = label,
            textStyle = textStyle,
            modifier = modifier,
            isError = isError,
            singleLine = true,
            keyboardActions =  KeyboardActions({keyboardActions?.invoke()}),
        )
        if (isError== true){
            Text(
                text = "Error message",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}