package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.domain.model.MarketItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface MarketRepository {
    fun getMarketList(): Flow<List<MarketItem>>
}

class MarketRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MarketRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMarketList(): Flow<List<MarketItem>> =
        remoteDataSource.getMarketList().flatMapLatest {
            flow {
                emit(it.toDomain())
            }.flowOn(Dispatchers.IO)
    }
}