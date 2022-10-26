package at.ac.fhcampuswien.bookapplication.widgets

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.bookapplication.ui.theme.AppColors
import at.ac.fhcampuswien.bookapplication.ui.theme.AppTypography
import at.ac.fhcampuswien.bookapplication.utils.DateTimeUtils
import java.time.LocalDate
import java.time.ZoneId
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
    enabled: Boolean = true,
    readOnly: Boolean = false,
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
            enabled = enabled,
            readOnly = readOnly,
            isError = isError,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppColors.Black44494D,
                focusedIndicatorColor = AppColors.Black44494D,
                unfocusedIndicatorColor = AppColors.GrayC7C7CC,
                backgroundColor = AppColors.White,
            ),
            shape = RoundedCornerShape(6.dp),
            trailingIcon = trailingIcon,
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
    onValueChange: (Date) -> Unit = {},
    label: String,
    hint: String = "",
    isError: Boolean = false,
    error: String? = null,
    textStyle: TextStyle = AppTypography.body1,
) {
    val context = LocalContext.current
    var date by remember { mutableStateOf<Date?>(null) }
    val interactionSource = remember { MutableInteractionSource() }
    CustomOutlineTextField(
        text = DateTimeUtils.formatDate(date),
        hint = hint,
        label = label,
        enabled = false,
        textStyle = textStyle,
        isError = isError,
        error = error,
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = null,
                tint = AppColors.GrayC7C7CC
            )
        },
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                showDatePicker(context = context,
                    currentDate = date,
                    onConfirm = { newDate ->
                        date = newDate
                        onValueChange.invoke(newDate)
                    })
            }
    )
}

private fun showDatePicker(context: Context, onConfirm: (Date) -> Unit, currentDate: Date? = null) {
    val calendar = Calendar.getInstance()
    if (currentDate != null) {
        calendar.time = currentDate
    }
    val currentYear = calendar[Calendar.YEAR]
    val currentMonth = calendar[Calendar.MONTH]
    val currentDay = calendar[Calendar.DAY_OF_MONTH]
    val now = System.currentTimeMillis()
    val dialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            val localDate = LocalDate.of(year, month, day)
            val date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            onConfirm.invoke(date)
        },
        currentYear,
        currentMonth,
        currentDay,
    )
    dialog.datePicker.maxDate = now
    dialog.show()
}


@Preview
@Composable
fun DateTimeTextFieldPreview() {
    return DateTimeTextField(label = "Date")
}