package com.matin.youtech.crypto.domain

data class Portfolio(
    val id: String,
    val marketItems: List<MarketItem>
)