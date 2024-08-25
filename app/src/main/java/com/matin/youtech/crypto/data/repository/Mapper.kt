package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.model.MarketItemNetwork

fun List<MarketItemNetwork>.toDomain() = map { it.toDomain() }