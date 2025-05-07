package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.di.ioDispatcher
import com.matin.youtech.crypto.domain.MarketRepository
import com.matin.youtech.crypto.domain.model.MarketItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    MarketRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMarketList(): Flow<List<MarketItem>> =
        remoteDataSource.getMarketList().map {
            it.toDomain()
        }.flowOn(ioDispatcher)
}