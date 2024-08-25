package com.matin.youtech.crypto.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.matin.youtech.crypto.designsystem.theme.LocalAdditionalColors

@Composable
fun pickPriceChangeColor(
    priceChange: String,
): Color {
    val additionalColors = LocalAdditionalColors.current
    return if (priceChange.dropLast(1).toFloat() >= 0) additionalColors.positiveRate else additionalColors.negativeRate
}

fun String.addPriceChangeSign() = if (this.dropLast(1).toFloat() > 0) "+${this}%" else "-${this}"