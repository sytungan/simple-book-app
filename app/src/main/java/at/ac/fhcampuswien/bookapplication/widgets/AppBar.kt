package at.ac.fhcampuswien.bookapplication.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography

@Composable
fun AppBar(title: String) {
    TopAppBar(
        backgroundColor = AppColors.Blue1F63DA,
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = AppTypography.h4)
        }
    }
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar("Book template")
}