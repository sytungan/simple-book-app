package at.ac.fhcampuswien.bookapplication.utils


import android.annotation.SuppressLint
import java.math.BigDecimal
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern

@SuppressLint("SimpleDateFormat")
object DateTimeUtils {
    private const val shortDateFormat = "yyyy-MM-dd"

    fun formatDate(date: Date?) : String {
        if (date == null) return ""
        return SimpleDateFormat(shortDateFormat).format(date)
    }

    fun formatTimeStamp(timestamp: Long) : String {
        return DateTimeFormatter
            .ofPattern(shortDateFormat)
            .withZone(ZoneId.systemDefault())
            .format(Instant.ofEpochSecond(timestamp))
    }

    fun parseTimeStamp(timeStamp: Long) : Date {
        return Date(timeStamp)
    }

    fun currentTimeStamp() : Long {
        return Instant.now().epochSecond
    }

    fun dateToTimeStamp(date: Date) : Long {
        return 0
    }
}