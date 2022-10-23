package at.ac.fhcampuswien.bookapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors.Black44494D
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors.Gray8E8E93

// Set of Material typography styles to start with
val AppTypography = Typography(
    defaultFontFamily = FontFamily.SansSerif,
    body1 = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
        color = Gray8E8E93
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W300,
        color = Gray8E8E93,
        fontSize = 12.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)