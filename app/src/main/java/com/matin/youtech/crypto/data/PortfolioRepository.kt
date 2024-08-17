package com.matin.youtech.crypto.data

import android.util.Log
import com.matin.youtech.crypto.data.local.LocalDataSource
import com.matin.youtech.crypto.data.model.toDomain
import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.domain.Portfolio
import com.matin.youtech.crypto.domain.PortfolioParameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PortfolioRepository {
    suspend fun fetchPortfolioFromServer(): Portfolio
    suspend fun fetchPortfolioFromStorage(): Portfolio
    fun observePortfolio(params: PortfolioParameter, forceReload: Boolean): Flow<Data<Portfolio>>
}

class PortfolioRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dataFlowManagerFactory: DataFlowManagerFactory
) : PortfolioRepository {

    private val dataFlowManager =
        dataFlowManagerFactory.create<PortfolioParameter, Portfolio>(
            fetchFromNetwork = { fetchPortfolioFromServer() })

    override suspend fun fetchPortfolioFromServer(): Portfolio =
            remoteDataSource.getPortfolio().toDomain().also {
                Log.d("PortfolioRepositoryImpl", "fetchPortfolioFromServer: $it")
        }

    override suspend fun fetchPortfolioFromStorage() = withContext(Dispatchers.IO) {
        localDataSource.getPortfolio().toDomain()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun observePortfolio(params: PortfolioParameter, forceReload: Boolean) =
        dataFlowManager.observe(params = params, forceReload = forceReload)
}

