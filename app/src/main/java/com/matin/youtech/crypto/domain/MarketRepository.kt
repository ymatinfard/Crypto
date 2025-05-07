package com.matin.youtech.crypto.domain

import com.matin.youtech.crypto.domain.model.MarketItem
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun getMarketList(): Flow<List<MarketItem>>
}