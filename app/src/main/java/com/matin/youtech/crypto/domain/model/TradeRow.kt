package com.matin.youtech.crypto.domain.model

data class TradeRow(
    val title: String,
    val children: List<TradeItem>
) : Component

data class TradeItem(
    val coinName: String,
    val iconUrl: String,
    val price: String,
    val change: String
) : Component
