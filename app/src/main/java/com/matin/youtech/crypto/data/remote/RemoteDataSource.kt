package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.getFakeMarketList
import com.matin.youtech.crypto.data.model.MarketListItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoteDataSource {
    fun getMarketList(): Flow<List<MarketListItemEntity>>
}

class RemoteDataSourceImpl @Inject constructor(private val scope: CoroutineScope) :
    RemoteDataSource {
    override fun getMarketList(): Flow<List<MarketListItemEntity>> = flow {
        while (true) {
            delay(2000)
            emit(getFakeMarketList())
        }
    }
}