package at.ac.fhcampuswien.bookapplication.ui.favoriate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.bookapplication.models.Book
import at.ac.fhcampuswien.bookapplication.navigation.AppScreen
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography
import at.ac.fhcampuswien.bookapplication.viewmodels.FavoriteViewModel
import at.ac.fhcampuswien.bookapplication.widgets.AppBar
import at.ac.fhcampuswien.bookapplication.widgets.BookItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    viewModel: FavoriteViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    Scaffold(
        backgroundColor = AppColors.GrayF5F6F8,
        topBar = { AppBar(title = AppScreen.Favorite.title) }
    ) {
        Surface(
            color = AppColors.White,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            val data = if (uiState.sortDesc) {
                uiState.books.sortedByDescending { it.date }
            } else {
                uiState.books.sortedBy { it.date }
            }
            SwipeRefresh(
                state = SwipeRefreshState(isRefreshing = uiState.loading),
                swipeEnabled = true,
                onRefresh = {
                    viewModel.getAllBookFromDB()
                }
            ) {
                if (data.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        EmptyFavoriteBook()
                    }

                } else {
                    Column() {
                        val interactionSource = remember { MutableInteractionSource() }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    viewModel.toggleSort()
                                },
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Sorted by publication date:", style = AppTypography.h4)
                            val sortStatus = if (uiState.sortDesc) "Descending" else "Ascending"
                            Text(text = sortStatus, style = AppTypography.h4, textDecoration = TextDecoration.Underline)
                        }
                        Divider(
                            color = AppColors.GrayCBD5E1,
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        ListFavoriteBook(data) {
                            viewModel.deleteBookFromDB(it)
                        }
                    }

                }
            }

        }

    }
}

@Composable
fun EmptyFavoriteBook() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .height(400.dp)
    ) {
        Text(
            text = "No books have been created yet.",
            style = AppTypography.h5.copy(color = AppColors.OrangeDB6E27)
        )
    }
}

@Composable
fun ListFavoriteBook(data: List<Book>, onClickRemove: (Book) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(12.dp)
    ) {
        itemsIndexed(data) { index, book ->
            BookItem(
                name = book.name,
                author = book.author,
                date = book.date.toLong(),
                iSBN = book.iBSN,
                onClickRemove = {
                    onClickRemove.invoke(book)
                }
            )
            if (index < data.lastIndex) {
                Divider(
                    color = AppColors.GrayCBD5E1,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(NavHostController(context = LocalContext.current))
}