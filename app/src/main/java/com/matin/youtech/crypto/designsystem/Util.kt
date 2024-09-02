package com.matin.youtech.crypto.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.matin.youtech.crypto.designsystem.theme.LocalAdditionalColors

@Composable
fun pickPriceChangeColor(
    priceChange: String,
): Color {
    val additionalColors = LocalAdditionalColors.current
    val parsedValue = priceChange.toFloatOrNull() ?: return Color.Gray
    return if (parsedValue >= 0) additionalColors.positiveRate else additionalColors.negativeRate
}

fun String.addPriceChangeSign() = if (this.toFloat() > 0) "+${this}%" else "-${this}%"

const val SCREEN_SIDE_PADDING = 16