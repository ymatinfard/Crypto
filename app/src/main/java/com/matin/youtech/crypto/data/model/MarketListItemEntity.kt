package com.matin.youtech.crypto.data.model

data class MarketListItemEntity(
    val coinName: String = "BTC",
    val coinUrl: String = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: String = "123.123"
)
