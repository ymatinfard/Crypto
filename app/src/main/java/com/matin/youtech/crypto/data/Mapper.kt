package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.MarketListItemEntity

fun List<MarketListItemEntity>.toDomain() = map { it.toDomain() }