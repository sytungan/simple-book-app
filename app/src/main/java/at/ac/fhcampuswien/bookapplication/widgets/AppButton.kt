package at.ac.fhcampuswien.bookapplication.widgets

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.BookApplicationLectureTheme

@Composable
fun AppButton(
    onClick: () -> Unit = {},
    title: String,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppColors.Purple700,
        ),
    ) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    BookApplicationLectureTheme {
        AppButton(title = "Some thing")
    }
}