package com.matin.youtech.crypto.domain.model

import java.math.BigDecimal

data class TradeItem(
    val coinName: String,
    val iconUrl: String,
    val price: BigDecimal,
    val change: String
)
