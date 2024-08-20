package com.matin.youtech.crypto.domain.model

import java.math.BigDecimal

data class MarketItem(
    val coinName: String = "",
    val coinUrl: String = "https",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: BigDecimal = BigDecimal("123.123")
)