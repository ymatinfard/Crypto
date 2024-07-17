package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.fakeMarketList
import com.matin.youtech.crypto.data.model.MarketListItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

interface RemoteDataSource {
    fun getMarketList(): Flow<List<MarketListItemEntity>>
}

class RemoteDataSourceImpl(private val scope: CoroutineScope) : RemoteDataSource {
    override fun getMarketList(): Flow<List<MarketListItemEntity>> = flow {
        scope.launch {
            while (true) {
                delay(1000)
                emit(fakeMarketList)
            }
        }
    }
}