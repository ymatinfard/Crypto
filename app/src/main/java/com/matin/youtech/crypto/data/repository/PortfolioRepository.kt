package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.local.LocalDataSource
import com.matin.youtech.crypto.data.model.toDomain
import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.di.ioDispatcher
import com.matin.youtech.crypto.domain.PortfolioParameter
import com.matin.youtech.crypto.domain.PortfolioRepository
import com.matin.youtech.crypto.domain.model.Portfolio
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.withContext
import java.util.WeakHashMap
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dataFlowManagerFactory: DataFlowManagerFactory,
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher,
) : PortfolioRepository {

    private val memoryCache: WeakHashMap<String, Portfolio> = WeakHashMap()
    private val dataFlowManager =
        dataFlowManagerFactory.create<PortfolioParameter, Portfolio>(
            fetchFromNetwork = { fetchPortfolioFromServer() },
            fetchFromMemory = { params ->
                memoryCache[params.id]
            },
            saveToMemory = { _, portfolio ->
                memoryCache[portfolio.id] = portfolio
            }
        )

    override suspend fun fetchPortfolioFromServer(): Portfolio =
        remoteDataSource.getPortfolio().toDomain()

    override suspend fun fetchPortfolioFromStorage() = withContext(ioDispatcher) {
        localDataSource.getPortfolio().toDomain()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun observePortfolio(params: PortfolioParameter, forceReload: Boolean) =
        dataFlowManager.observe(params = params, forceReload = forceReload)
}

