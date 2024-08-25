package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.model.Brand
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.domain.model.TradeItem
import com.matin.youtech.crypto.domain.model.TradeRow
import org.junit.Assert.*
import org.junit.Test

class ScreenNetworkTest {
    @Test
    fun `test ScreenNetwork mapper`() {
        val screenNetwork = ScreenNetwork(
            title = "Test Screen",
            components = listOf(
                BrandNetwork(
                    name = "Brand 1",
                    image = "http://example_url.com"
                ),
                TradeRowNetwork(
                    title = "Trade Row",
                    componentType = ComponentType.TradeRowComponent,
                    children = listOf(
                        TradeItemNetwork(
                            coinName = "Coin 1",
                            iconUrl = "http://example_url.com",
                            price = "100",
                            change = "1%"
                        )
                    )
                )
            )
        )

        val expected = Screen(
            title = "Test Screen",
            components = listOf(
                Brand(name = "Brand 1", image = "http://example_url.com"),
                TradeRow(
                    title = "Trade Row",
                    children = listOf(
                        TradeItem(
                            coinName = "Coin 1",
                            iconUrl = "http://example_url.com",
                            price = "100",
                            change = "1%"
                        )
                    )
                )
            )
        )

        assertEquals(expected, screenNetwork.toDomain())
    }
}