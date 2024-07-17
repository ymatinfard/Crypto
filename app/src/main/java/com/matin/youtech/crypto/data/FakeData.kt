package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.MarketListItemEntity
import kotlin.random.Random

fun getFakeMarketList() = listOf(
    MarketListItemEntity(
        coinName = "BTC",
        coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png",
        ticker = "123",
        priceChange = "$${Random.nextInt(1, 10)}",
        price = Random.nextDouble(from = 40000.0, 60000.0).toString()
    ),
    MarketListItemEntity(
        coinName = "DASH",
        coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/dash.png",
        ticker = "354",
        priceChange = "$${Random.nextInt(1, 20)}",
        price = Random.nextDouble(from = 10000.0, 40000.0).toString()
    ),
    MarketListItemEntity(
        coinName = "DOT",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dot.png?raw=true",
        ticker = "214",
        priceChange = "$${Random.nextInt(1, 5)}",
        price = Random.nextDouble(from = 20000.0, 35000.0).toString()
    ),
)