package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.MarketListItemNetwork

fun List<MarketListItemNetwork>.toDomain() = map { it.toDomain() }