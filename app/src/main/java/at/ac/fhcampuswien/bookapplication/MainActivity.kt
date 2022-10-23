package at.ac.fhcampuswien.bookapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import at.ac.fhcampuswien.bookapplication.ui.theme.MovieApplicationLectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApplicationLectureTheme {
                BookApp()
            }
        }
    }
}