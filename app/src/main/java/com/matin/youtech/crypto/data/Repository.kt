package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.domain.MarketListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
    fun getMarketList(): Flow<List<MarketListItem>>
}

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMarketList(): Flow<List<MarketListItem>> =
        remoteDataSource.getMarketList().flatMapLatest {
            flow {
                emit(it.toDomain())
            }
    }
}