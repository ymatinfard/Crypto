package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.fakePortfolio
import com.matin.youtech.crypto.data.getFakeMarketList
import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.PortfolioNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoteDataSource {
    fun getMarketList(): Flow<List<MarketItemNetwork>>
    suspend fun getPortfolio(): PortfolioNetwork
}

class RemoteDataSourceImpl @Inject constructor(private val scope: CoroutineScope) :
    RemoteDataSource {
    override fun getMarketList(): Flow<List<MarketItemNetwork>> = flow {
        while (true) {
            delay(500)
            emit(getFakeMarketList())
        }
    }

    override suspend fun getPortfolio(): PortfolioNetwork {
        delay(2000)
        return fakePortfolio()
    }
}