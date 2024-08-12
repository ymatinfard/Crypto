package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.fakePortfolio
import com.matin.youtech.crypto.data.getFakeMarketList
import com.matin.youtech.crypto.data.model.MarketListItemNetwork
import com.matin.youtech.crypto.data.model.PortfolioItemNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoteDataSource {
    fun getMarketList(): Flow<List<MarketListItemNetwork>>
    suspend fun getPortfolio(): List<PortfolioItemNetwork>
}

class RemoteDataSourceImpl @Inject constructor(private val scope: CoroutineScope) :
    RemoteDataSource {
    override fun getMarketList(): Flow<List<MarketListItemNetwork>> = flow {
        while (true) {
            delay(2000)
            emit(getFakeMarketList())
        }
    }

    override suspend fun getPortfolio(): List<PortfolioItemNetwork> {
        delay(2000)
        return fakePortfolio()
    }
}