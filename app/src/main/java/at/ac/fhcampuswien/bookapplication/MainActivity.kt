package at.ac.fhcampuswien.bookapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import at.ac.fhcampuswien.bookapplication.ui.theme.BookApplicationLectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppSingletons.provide(this)
        super.onCreate(savedInstanceState)
        setContent {
            BookApplicationLectureTheme {
                BookApp()
            }
        }
    }
}