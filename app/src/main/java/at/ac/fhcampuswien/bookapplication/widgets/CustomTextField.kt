package at.ac.fhcampuswien.bookapplication.widgets

import android.app.DatePickerDialog
import android.content.Context
import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography
import java.time.LocalDate
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import java.util.*


@Composable
fun CustomOutlineTextField(
    modifier: Modifier = Modifier.padding(8.dp),
    text: String,
    onValueChange: (String) -> Unit = {},
    label: String,
    hint: String = "",
    textStyle: TextStyle = AppTypography.body1,
    isError: Boolean = false,
    trailingIcon: @Composable () -> Unit = {},
    error: String? = null,
    keyboardActions: () -> Unit = {}
) {
    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    style = AppTypography.h5.copy(AppColors.GrayC7C7CC),
                )
            },
            label = {
                Text(
                    text = label,
                    style = AppTypography.h5.copy(AppColors.Black44494D),
                )
            },
            textStyle = textStyle,
            modifier = modifier,
            isError = isError,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppColors.Black44494D,
                focusedIndicatorColor = AppColors.Black44494D,
                unfocusedIndicatorColor = AppColors.GrayC7C7CC,
                backgroundColor = AppColors.White,
            ),
            shape = RoundedCornerShape(6.dp),
            keyboardActions = KeyboardActions { keyboardActions.invoke() },
        )
        if (isError && error != null) {
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                style = AppTypography.subtitle2,
            )
        }
    }
}

@Composable
fun DateTimeTextField(
    onValueChange: (Date) -> Unit,
    label: String,
    hint: String = "",
    textStyle: TextStyle = AppTypography.body1,
) {
    val context = LocalContext.current
    val text by remember { mutableStateOf("") }
    CustomOutlineTextField(
        text = "",
        hint = hint,
        label = label,
        textStyle = textStyle,
        trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                showDatePicker(context = context)
            }
    )
}

private fun showDatePicker(context: Context) {
    val dialog = DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { _, year, month, day ->
            LocalDate.of(year, month, day)
        },
        2022,
        12,
        10,
    )

}