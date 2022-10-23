package at.ac.fhcampuswien.bookapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography
import at.ac.fhcampuswien.bookapplication.ui.theme.MovieApplicationLectureTheme
import at.ac.fhcampuswien.bookapplication.utils.DateTimeUtils
import java.util.*

@Composable
fun BookItem(name: String, author: String, date: Date, onClick: () -> Unit = {}) {
    Surface(
//        color = AppColors.Purple500,
        modifier = Modifier
//            .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 8.dp),
        ) {
            Icon(Icons.Rounded.AccountBox, contentDescription = "Localized description")
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(text = name, style = AppTypography.h5)
                Text(text = author, style = AppTypography.subtitle2)
                Text(text = DateTimeUtils.formatDate(date), style = AppTypography.subtitle2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    MovieApplicationLectureTheme {
        BookItem(name = "Book 1", author = "Ronaldo", date = Date())
    }
}