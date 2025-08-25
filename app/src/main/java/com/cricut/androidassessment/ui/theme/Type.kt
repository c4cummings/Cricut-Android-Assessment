package com.cricut.androidassessment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.cricut.androidassessment.R

// Generated from Material Theme Builder: https://material-foundation.github.io/material-theme-builder/

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Simonetta"),
        fontProvider = provider,
    )
)

private val headlineFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Simonetta"),
        fontProvider = provider,
    )
)

private val titleFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Simonetta"),
        fontProvider = provider,
    )
)

private val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Simonetta"),
        fontProvider = provider,
    )
)

private val labelFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Simonetta"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
private val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = headlineFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = headlineFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = headlineFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = titleFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = titleFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = titleFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = labelFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = labelFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = labelFontFamily),
)
