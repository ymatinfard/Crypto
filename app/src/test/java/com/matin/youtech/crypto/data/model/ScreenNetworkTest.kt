package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.model.Banner
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.domain.model.TradeItem
import com.matin.youtech.crypto.domain.model.TradeRow
import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenNetworkTest {
    @Test
    fun `test ScreenNetwork mapper`() {
        val screenNetwork = ScreenNetwork(
            title = "Test Screen",
            components = listOf(
                BannerNetwork(
                    componentType = ComponentType.BannerComponent,
                    title = "Banner",
                    description = listOf("Test1", "Test2"),
                    iconUrl = "http://example_url.com"
                ),
                TradeRowNetwork(
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
                Banner(
                    title = "Banner",
                    description = listOf("Test1", "Test2"),
                    iconUrl = "http://example_url.com"
                ),
                TradeRow(
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