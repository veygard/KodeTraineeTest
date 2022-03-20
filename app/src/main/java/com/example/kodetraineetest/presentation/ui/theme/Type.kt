package com.example.kodetraineetest.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.kodetraineetest.R

// Set of Material typography styles to start with

val InterFont = FontFamily(
    Font(R.font.inter_black),
    Font(R.font.inter_bold),
    Font(R.font.inter_light),
    Font(R.font.inter_medium),
    Font(R.font.inter_regular),
    Font(R.font.inter_semibold),
    Font(R.font.inter_thin),

)

val headlineMedium = TextStyle(
    fontFamily = InterFont,
    fontSize = 16.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp,
    textAlign = TextAlign.Start,
)

val caption1Regular = TextStyle(
    fontFamily = InterFont,
    fontSize = 13.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 16.sp,
    textAlign = TextAlign.Start,
)

val subheadMedium = TextStyle(
    fontFamily = InterFont,
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 18.sp,
    textAlign = TextAlign.Start,
)

val textMedium = TextStyle(
    fontFamily = InterFont,
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp,
    textAlign = TextAlign.Start,
)
val textSemibold = TextStyle(
    fontFamily = InterFont,
    fontSize = 15.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 20.sp,
    textAlign = TextAlign.Start,
)
val title2SemiBold = TextStyle(
    fontFamily = InterFont,
    fontSize = 20.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 24.sp,
    textAlign = TextAlign.Center,
)

val title1Bold = TextStyle(
    fontFamily = InterFont,
    fontSize = 24.sp,
    fontWeight = FontWeight.W700,
    lineHeight = 28.sp,
    textAlign = TextAlign.Center,
)