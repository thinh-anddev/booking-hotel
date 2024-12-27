package com.example.booking_hotel.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDate(
    millis: Long
): LocalDate {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault()).toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDateWithFormatter(
    date: LocalDate,
    dateTimeFormatter: DateTimeFormatter
): LocalDate {
    val dateInMillis = LocalDate.parse(
        date.format(dateTimeFormatter),
        dateTimeFormatter
    )
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    return Instant.ofEpochMilli(dateInMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(
    date: LocalDate
): String {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    val dateInMillis = convertMillisToLocalDateWithFormatter(date, dateFormatter)
    return dateFormatter.format(dateInMillis)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDate(): LocalDate {
    return LocalDate.now(ZoneId.systemDefault())
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseStringToLocalDate(dateString: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    return LocalDate.parse(dateString, formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateNumberNightsFromString(checkInDateString: String, checkOutDateString: String): Long {
    val checkInDate = parseStringToLocalDate(checkInDateString)
    val checkOutDate = parseStringToLocalDate(checkOutDateString)
    return ChronoUnit.DAYS.between(checkInDate, checkOutDate)
}