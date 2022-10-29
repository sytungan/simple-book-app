package at.ac.fhcampuswien.bookapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.material.icons.sharp.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun BookItem(
    name: String,
    author: String,
    date: Long,
    iSBN: String,
    onClick: () -> Unit = {},
    onClickRemove: () -> Unit = {},
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 8.dp),
            ) {
                Icon(Icons.Sharp.MailOutline, contentDescription = "Localized description")
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(text = name, style = AppTypography.h4.copy(fontWeight = FontWeight.Bold))
                    Text(text = author, style = AppTypography.body1)
                    Text(text = DateTimeUtils.formatTimeStamp(date), style = AppTypography.subtitle2)
                    Text(text = iSBN, style = AppTypography.subtitle2)
                }
            }

            IconButton(onClick = {onClickRemove.invoke()}) {
                Icon(Icons.Sharp.Delete, contentDescription = null)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    MovieApplicationLectureTheme {
        BookItem(name = "Book 1", author = "Ronaldo", iSBN = "", date = 0)
    }
}