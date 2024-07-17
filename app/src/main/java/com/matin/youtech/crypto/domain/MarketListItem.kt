package com.matin.youtech.crypto.domain

data class MarketListItem(
    val coinName: String = "",
    val coinUrl: String = "https",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: String = "123.123"
)