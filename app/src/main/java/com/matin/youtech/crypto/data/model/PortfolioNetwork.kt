package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Portfolio

data class PortfolioNetwork(
    val id: String = "",
    val marketItems: List<MarketItemNetwork> = emptyList()
) {
    fun toDomain(): Portfolio {
        val items = marketItems.map { item ->
            item.toDomain()
        }

        return Portfolio(
            id = id,
            marketItems = items
        )
    }
}
