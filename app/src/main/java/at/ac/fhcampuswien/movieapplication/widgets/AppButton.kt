package at.ac.fhcampuswien.movieapplication.widgets

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.movieapplication.ui.theme.AppColors
import at.ac.fhcampuswien.movieapplication.ui.theme.MovieApplicationLectureTheme

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
fun DefaultPreview() {
    MovieApplicationLectureTheme {
        AppButton(title = "Con cac")
    }
}