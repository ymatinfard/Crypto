package com.matin.youtech.crypto.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryDark,
    tertiary = tertiaryDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    tertiaryContainer = tertiaryContainerDark,
    background = backgroundDark,
    error = errorDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    tertiary = tertiaryLight,
    surfaceVariant = surfaceVariantLight,
    tertiaryContainer = tertiaryContainerLight,
    background = backgroundLight,
    error = errorLight,
)

@Immutable
data class AdditionalColorScheme(
    val positiveRate: Color = Color.Unspecified,
    val negativeRate: Color = Color.Unspecified,
)

private val LightAdditionalColor =
    AdditionalColorScheme(positiveRate = positiveRateLight, negativeRate = negativeRateLight)
private val DarkAdditionalColor =
    AdditionalColorScheme(positiveRate = positiveRateDark, negativeRate = negativeRateDark)

val LocalAdditionalColors = staticCompositionLocalOf { AdditionalColorScheme() }

@Composable
fun ProvideColors(
    colorScheme: ColorScheme,
    additionalColorScheme: AdditionalColorScheme,
    content: @Composable () -> Unit,
) {
    val additionalColorCache = remember { additionalColorScheme }

    CompositionLocalProvider(
        LocalAdditionalColors provides additionalColorCache,
        content = content
    )
}

@Composable
fun CryptoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val additionalColorScheme = if (darkTheme) DarkAdditionalColor else LightAdditionalColor

    ProvideColors(colorScheme = colorScheme, additionalColorScheme = additionalColorScheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}