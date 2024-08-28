package com.matin.youtech.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class ComponentType {
    BannerComponent,
    TradeRowComponent,
    TradeRowItemComponent,
    TradeBotComponent,
    RowTitleComponent,
    LineSpaceComponent,
}