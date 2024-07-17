package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.MarketListItem
import org.junit.Assert.*

import org.junit.Test
import java.math.BigDecimal

class MarketListItemEntityTest {

    @Test
    fun `Map MarketListItemEntity to MarketListItemDomain`() {
        val marketListItemEntity = MarketListItemEntity(
            coinName = "BTC",
            coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
            ticker = "123",
            priceChange = "3%",
            price = "123.123"
        )
        val marketListItemDomain = MarketListItem(
            coinName = "BTC",
            coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
            ticker = "123",
            priceChange = "3%",
            price = BigDecimal("123.12")
        )
        assertEquals(marketListItemDomain, marketListItemEntity.toDomain())
    }

    @Test
    fun `Map list of MarketListItemEntity to list of MarketListItemDomain`() {
        val marketListItemEntities = listOf(
            MarketListItemEntity(
                coinName = "BTC",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
                ticker = "123",
                priceChange = "3%",
                price = "123.123"
            ),
            MarketListItemEntity(
                coinName = "DASH",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dash.png",
                ticker = "354",
                priceChange = "3%",
                price = "345.346"
            )
        )

        val marketListItemDomains = listOf(
            MarketListItem(
                coinName = "BTC",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/btc.png",
                ticker = "123",
                priceChange = "3%",
                price = BigDecimal("123.12")
            ),
            MarketListItem(
                coinName = "DASH",
                coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dash.png",
                ticker = "354",
                priceChange = "3%",
                price = BigDecimal("345.34")
            )
        )

        assertEquals(marketListItemDomains, marketListItemEntities.toDomain())
    }
}