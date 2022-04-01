package com.example.kodetraineetest.util

import java.time.LocalDate

object Constants {
    const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"

    val CURRENT_YEAR = LocalDate.now().year.toString()
     val NEXT_YEAR = LocalDate.now().year.plus(1).toString()
}