package com.matin.youtech.crypto.sdui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.designsystem.Banner
import com.matin.youtech.crypto.designsystem.TradeRow
import com.matin.youtech.crypto.domain.model.Banner
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.domain.model.TradeRow


class UIRenderer(val screen: Screen) {
    @Composable
    fun render() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 6.dp)) {
            screen.components.forEach { component ->
                when (component) {
                    is Banner -> Banner(component)
                    is TradeRow -> TradeRow(component)
                    else -> {}
                }
            }
        }
    }
}