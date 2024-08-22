package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.MarketItem
import org.junit.Assert.*

import org.junit.Test
import java.math.BigDecimal

class MarketItemNetworkTest {

    @Test
    fun `Map MarketListItemEntity to MarketListItemDomain`() {
        val marketListItemNetwork = MarketItemNetwork(
            coinName = "BTC",
            coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
            ticker = "123",
            priceChange = "3%",
            price = "123.123"
        )
        val marketItemDomain = MarketItem(
            coinName = "BTC",
            coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
            ticker = "123",
            priceChange = "3%",
            price = BigDecimal("123.12")
        )
        assertEquals(marketItemDomain, marketListItemNetwork.toDomain())
    }

    @Test
    fun `Map list of MarketListItemEntity to list of MarketListItemDomain`() {
        val marketListItemEntities = listOf(
            MarketItemNetwork(
                coinName = "BTC",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
                ticker = "123",
                priceChange = "3%",
                price = "123.123"
            ),
            MarketItemNetwork(
                coinName = "DASH",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dash.png",
                ticker = "354",
                priceChange = "3%",
                price = "345.346"
            )
        )

        val marketItemDomains = listOf(
            MarketItem(
                coinName = "BTC",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
                ticker = "123",
                priceChange = "3%",
                price = BigDecimal("123.12")
            ),
            MarketItem(
                coinName = "DASH",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dash.png",
                ticker = "354",
                priceChange = "3%",
                price = BigDecimal("345.34")
            )
        )

        assertEquals(marketItemDomains, marketListItemEntities.toDomain())
    }
}