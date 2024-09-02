package com.matin.youtech.crypto.domain.model

import com.matin.youtech.annotaions.Component


data class TradeRow(
    val children: List<TradeItem>
) : Component

data class TradeItem(
    val coinName: String,
    val iconUrl: String,
    val price: String,
    val change: String
) : Component
