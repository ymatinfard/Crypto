package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.MarketListItemEntity
import kotlin.random.Random

val fakeMarketList = listOf(
    MarketListItemEntity(
        coinName = "BTC",
        ticker = "123",
        priceChange = "$${Random.nextInt(1, 10)}",
        price = Random.nextDouble(from = 40000.0, 60000.0).toString()
    ),
    MarketListItemEntity(
        coinName = "ATOM",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/atom.png",
        ticker = "354",
        priceChange = "$${Random.nextInt(1, 20)}",
        price = Random.nextDouble(from = 10000.0, 40000.0).toString()
    ),
    MarketListItemEntity(
        coinName = "BNB",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/bnb.png",
        ticker = "214",
        priceChange = "$${Random.nextInt(1, 5)}",
        price = Random.nextDouble(from = 20000.0, 35000.0).toString()
    ),
)