package at.ac.fhcampuswien.bookapplication.widgets


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.bookapplication.navigation.AppScreen
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography

@Composable
fun AppBottomNavigationBar(
    currentIndex: Int = 0,
    onIndexChanged: (Int, AppScreen) -> Unit
) {
    val bottomBarContent = listOf(AppScreen.Favorite, AppScreen.NewBook)
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        bottomBarContent.forEachIndexed { index, appScreen ->
            val selected = index == currentIndex
            BottomNavigationItem(
                label = { Text(appScreen.title, style = AppTypography.h5) },
                selected = selected,
                onClick = {
                    if (!selected) {
                        onIndexChanged.invoke(index, appScreen)
                    }
                },
                icon = appScreen.icon,
                unselectedContentColor = AppColors.Gray8E8E93,
                selectedContentColor = AppColors.OrangeDB6E27,
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun AppBottomNavigationBarPreview() {
    AppBottomNavigationBar(0) { _, _ ->

    }
}