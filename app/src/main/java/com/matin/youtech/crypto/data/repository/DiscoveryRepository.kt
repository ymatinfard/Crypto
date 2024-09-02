package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.model.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DiscoveryRepository {
    suspend fun getDiscoveryScreen(): Screen
}

class DiscoveryRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): DiscoveryRepository {

    override suspend fun getDiscoveryScreen(): Screen = withContext(Dispatchers.IO) {
        remoteDataSource.getDiscoveryScreen().toDomain()
    }
}

