package com.example.kodetraineetest.presentation.screens.compose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.kodetraineetest.R


val InterFont = FontFamily(
    Font(R.font.inter_regular),
)

val headlineMedium = TextStyle(
    fontFamily = InterFont,
    fontSize = 16.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp,
    textAlign = TextAlign.Start,
)
val headlineRegular = TextStyle(
    fontFamily = InterFont,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 20.sp,
    textAlign = TextAlign.Center,
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
val subheadSemibold = TextStyle(
    fontFamily = InterFont,
    fontSize = 14.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 18.sp,
    textAlign = TextAlign.Right,
)
val textMedium = TextStyle(
    fontFamily = InterFont,
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp,
    textAlign = TextAlign.Start,
)
val textRegular = TextStyle(
    fontFamily = InterFont,
    fontSize = 15.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 20.sp,
    textAlign = TextAlign.Right,
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

val title3regular = TextStyle(
    fontFamily = InterFont,
    fontSize = 17.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 22.sp,
    textAlign = TextAlign.Center,
)

val title3SemiBold = TextStyle(
    fontFamily = InterFont,
    fontSize = 17.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 22.sp,
    textAlign = TextAlign.Center,
)

val title1Bold = TextStyle(
    fontFamily = InterFont,
    fontSize = 24.sp,
    fontWeight = FontWeight.W700,
    lineHeight = 28.sp,
    textAlign = TextAlign.Center,
)