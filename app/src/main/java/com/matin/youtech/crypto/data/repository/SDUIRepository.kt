package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.di.ioDispatcher
import com.matin.youtech.crypto.domain.model.Screen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SDUIRepository {
    suspend fun getDiscoveryScreen(): Screen
    suspend fun getScreen(id: String): Screen
}

class SDUIRepositoryImpl @Inject constructor(
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource
) : SDUIRepository {

    override suspend fun getDiscoveryScreen(): Screen = withContext(ioDispatcher) {
        remoteDataSource.getDiscoveryScreen().toDomain()
    }

    override suspend fun getScreen(id: String): Screen = withContext(ioDispatcher) {
        remoteDataSource.getScreen(id).toDomain()
    }
}

