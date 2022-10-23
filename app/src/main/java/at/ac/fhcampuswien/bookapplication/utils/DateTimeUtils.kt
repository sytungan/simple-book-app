package at.ac.fhcampuswien.bookapplication.utils


import android.annotation.SuppressLint
import java.math.BigDecimal
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object DateTimeUtils {
    private const val shortDateFormat = "mm/dd/yyyy"

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Date) : String {
        return SimpleDateFormat(shortDateFormat).format(date)
    }
}