package com.matin.youtech.crypto.domain.model

import com.matin.youtech.annotaions.Component


data class TradeBot(
    val name: String,
    val iconUrl: String,
    val roi: String,
    val minInvestment: String,
    val runTime: String,
    val copies: Int
): Component
