package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.domain.MarketListItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getMarketList(): Flow<List<MarketListItem>>
}

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getMarketList(): Flow<List<MarketListItem>> {
        TODO("Not yet implemented")
    }
}