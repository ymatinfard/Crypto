package com.matin.youtech.crypto.domain

import java.math.BigDecimal

data class MarketListItem(
    val coinName: String = "",
    val coinUrl: String = "https",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: BigDecimal = BigDecimal("123.123")
)