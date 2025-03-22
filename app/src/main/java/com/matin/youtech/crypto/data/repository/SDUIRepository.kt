package com.matin.youtech.crypto.data.repository

import android.util.Log
import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.di.ioDispatcher
import com.matin.youtech.crypto.domain.model.CryptoCoin
import com.matin.youtech.crypto.domain.model.Screen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

interface SDUIRepository {
    suspend fun getDiscoveryScreen(): Screen
    suspend fun getScreen(id: String): Screen
    val cryptoCoinPrice: SharedFlow<List<CryptoCoin>>
}

class SDUIRepositoryImpl @Inject constructor(
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource
) : SDUIRepository {

    private val scope = CoroutineScope(ioDispatcher)

    override suspend fun getDiscoveryScreen(): Screen = withContext(ioDispatcher) {
        remoteDataSource.getDiscoveryScreen().toDomain()
    }

    override suspend fun getScreen(id: String): Screen = withContext(ioDispatcher) {
        remoteDataSource.getScreen(id).toDomain()
    }

 private fun getCurrencyPrice() = flow {
        while (true) {
            delay(3000)
            emit(getFakeCryptoCoinList())
            Log.d("Crypto", "repository-getCurrencyPrice is running")
        }
    }.flowOn(ioDispatcher)

    override val cryptoCoinPrice : SharedFlow<List<CryptoCoin>> = getCurrencyPrice().shareIn(scope, SharingStarted.WhileSubscribed())
}

fun getFakeCryptoCoinList() = List(10) { index ->
    val randomPrice = Random.nextDouble(1000.0, 2000.0)
    CryptoCoin("BTC$index", randomPrice, 10.0, 1.0)
}

