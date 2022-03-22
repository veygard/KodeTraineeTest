package com.example.kodetraineetest.util.extention

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

fun String.toLocalDate(): LocalDate? {
    return try {
        val formatter = ofPattern("yyyy-MM-dd", Locale.getDefault())
        LocalDate.parse(this, formatter)
    }catch (e: Exception){
        null
    }
}