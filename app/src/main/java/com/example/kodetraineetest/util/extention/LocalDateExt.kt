package com.example.kodetraineetest.util.extention

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.toDayMonthString(): String? {
    val formatter = DateTimeFormatter.ofPattern("dd MMM", Locale("ru"))
    return LocalDate.of(this.year, this.month, this.dayOfMonth).format(formatter)
}

fun LocalDate.toFullString(): String? {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
    return LocalDate.of(this.year, this.month, this.dayOfMonth).format(formatter)
}