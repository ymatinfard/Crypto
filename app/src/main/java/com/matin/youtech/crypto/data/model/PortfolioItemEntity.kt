package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.MarketItem
import com.matin.youtech.crypto.domain.Portfolio
import java.math.BigDecimal

data class PortfolioItemEntity(
    val coinName: String = "BTC",
    val coinUrl: String = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
    val ticker: String = "123",
    val priceChange: String = "3%",
    val price: String = "123.123"
)

fun List<PortfolioItemEntity>.toDomain(): Portfolio {
    val items = this.map { item ->
        MarketItem(
            coinName = item.coinName,
            coinUrl = item.coinUrl,
            ticker = item.ticker,
            priceChange = item.priceChange,
            price = BigDecimal(item.price).setScale(2, BigDecimal.ROUND_FLOOR)
        )
    }
    return Portfolio(
        id = "000",
        marketItems = items
    )
}