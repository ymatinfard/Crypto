package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.data.toAction
import com.matin.youtech.crypto.domain.model.MarketItem
import com.matin.youtech.crypto.sdui.Action
import java.math.BigDecimal

data class MarketItemNetwork(
    val coinName: String = "BTC",
    val coinUrl: String = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
    val ticker: String = "123",
    val priceChange: String = "1.3%",
    val price: String = "123.123",
    val action: ActionNetwork? = null,
) {
    fun toDomain(): MarketItem = MarketItem(
        coinName = coinName,
        coinUrl = coinUrl,
        ticker = ticker,
        priceChange = priceChange,
        price = BigDecimal(price).setScale(2, BigDecimal.ROUND_FLOOR),
        action = action?.toAction()
    )
}
