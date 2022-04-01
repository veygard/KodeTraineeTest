package com.example.kodetraineetest.util

import android.content.Context
import com.example.kodetraineetest.R
import java.time.LocalDate
import java.time.Period

fun ageDescription(date: LocalDate?, context: Context): String {
    val age = date.let { Period.between(it, LocalDate.now()).years }

    return when {
        age % 100 in 11..14 -> "$age ${context.getString(R.string.years1)}"
        age % 10 == 1 -> "$age ${context.getString(R.string.years2)}"
        age % 10 in 2..4 -> "$age ${context.getString(R.string.years2)}"
        else -> "$age ${context.getString(R.string.years1)}"
    }
}