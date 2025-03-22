package com.matin.youtech.crypto.domain.model

data class CryptoCoin(
    val name: String = "",
    val price: Double = 0.0,
    val change: Double = 0.0,
    val changePercent: Double = 0.0,
)