package com.example.to_do.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.to_do.R


val Adlam = FontFamily(
    Font(R.font.adlam_regular, FontWeight.Normal)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Adlam,
        fontWeight = FontWeight.Normal,
        fontSize = 150.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Adlam,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Adlam,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Adlam,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Adlam,
        fontWeight = FontWeight.Bold,
        fontSize = 80.sp
    )
)