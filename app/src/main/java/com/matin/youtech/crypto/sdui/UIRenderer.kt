package com.matin.youtech.crypto.sdui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.designsystem.Banner
import com.matin.youtech.crypto.designsystem.LineSpace
import com.matin.youtech.crypto.designsystem.RowTitle
import com.matin.youtech.crypto.designsystem.TradeBot
import com.matin.youtech.crypto.designsystem.TradeRow
import com.matin.youtech.crypto.domain.model.Banner
import com.matin.youtech.crypto.domain.model.Component
import com.matin.youtech.crypto.domain.model.LineSpace
import com.matin.youtech.crypto.domain.model.RowTitle
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.domain.model.TradeBot
import com.matin.youtech.crypto.domain.model.TradeRow


object UIRenderer {
    @Composable
    fun render(screen: Screen) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp)
        ) {
            item {
                screen.components.forEach { component ->
                    componentRenderers[component::class.java]?.invoke(component)
                }
            }
        }
    }

    private val componentRenderers: Map<Class<*>, @Composable (Component) -> Unit> =
        mapOf(
            Banner::class.java to { component -> Banner(component as Banner) },
            TradeRow::class.java to { component -> TradeRow(component as TradeRow) },
            TradeBot::class.java to { component -> TradeBot(component as TradeBot) },
            RowTitle::class.java to { component -> RowTitle(component as RowTitle) },
            LineSpace::class.java to { component -> LineSpace(component as LineSpace)}
        )
}