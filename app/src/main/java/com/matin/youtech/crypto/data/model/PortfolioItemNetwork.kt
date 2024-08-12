package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.PortfolioItem
import java.math.BigDecimal

data class PortfolioItemNetwork(
    val coinName: String = "BTC",
    val coinUrl: String = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: String = "123.123"
) {
    fun toDomain(): PortfolioItem = PortfolioItem(
        coinName = coinName,
        coinUrl = coinUrl,
        ticker = ticker,
        priceChange = priceChange,
        price = BigDecimal(price).setScale(2, BigDecimal.ROUND_FLOOR)
    )
}
