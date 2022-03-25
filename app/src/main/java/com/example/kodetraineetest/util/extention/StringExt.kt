package com.example.kodetraineetest.util.extention

import android.content.Context
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat.getSystemService
import java.time.LocalDate
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

fun String.formatPhone(): String? {
    return try {
        "+7 (${this.substring(0,3)}) ${this.substring(4,7)} ${this.substring(8,10)} ${this.substring(10,this.length)}"
    } catch (e: java.lang.Exception){
        null
    }
}
fun String.formatPhoneForDial(): String =
        "+7${this}"