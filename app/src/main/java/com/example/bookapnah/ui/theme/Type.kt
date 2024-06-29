package com.example.bookapnah.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bookapnah.R

// Set of Material typography styles to start with

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)
val RobotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_light, FontWeight.Light),
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
)

val autography = FontFamily(
    Font(R.font.autography, FontWeight.Normal)
)

val playwite = FontFamily(
    Font(R.font.playwritees_extra_light,FontWeight.ExtraLight),
    Font(R.font.playwritees_light,FontWeight.Light),
    Font(R.font.playwritees_regular,FontWeight.Normal),
    Font(R.font.playwritees_thin,FontWeight.Thin)

)

val  handfine = FontFamily(
    Font(R.font.handfine, FontWeight.Normal)
)


val  handbook = FontFamily(
    Font(R.font.handbook, FontWeight.Normal)
)


val awesome = FontFamily(
    Font(R.font.awesome_font, FontWeight.Normal)
)

val ocean = FontFamily(
    Font(R.font.oncen, FontWeight.Normal)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)