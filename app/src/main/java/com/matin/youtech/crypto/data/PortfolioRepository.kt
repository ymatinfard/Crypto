package com.matin.youtech.crypto.data

import android.util.Log
import com.matin.youtech.crypto.data.local.LocalDataSource
import com.matin.youtech.crypto.data.model.PortfolioItemEntity
import com.matin.youtech.crypto.data.model.PortfolioItemNetwork
import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.domain.PortfolioItem
import com.matin.youtech.crypto.domain.PortfolioParameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PortfolioRepository {
    suspend fun fetchPortfolioFromServer(): List<PortfolioItem>
    suspend fun fetchPortfolioFromStorage(): List<PortfolioItem>
    fun observePortfolio(params: PortfolioParameter, forceReload: Boolean): Flow<Data<List<PortfolioItem>>>
}

class PortfolioRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dataFlowManagerFactory: DataFlowManagerFactory
) : PortfolioRepository {

    private val dataFlowManager =
        dataFlowManagerFactory.create<PortfolioParameter, List<PortfolioItem>>(
            fetchFromNetwork = { fetchPortfolioFromServer() })

    override suspend fun fetchPortfolioFromServer(): List<PortfolioItem> =
            remoteDataSource.getPortfolio().map(PortfolioItemNetwork::toDomain).also {
                Log.d("PortfolioRepositoryImpl", "fetchPortfolioFromServer: $it")
        }

    override suspend fun fetchPortfolioFromStorage() = withContext(Dispatchers.IO) {
        localDataSource.getPortfolio().map(PortfolioItemEntity::toDomain)
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun observePortfolio(params: PortfolioParameter, forceReload: Boolean) =
        dataFlowManager.observe(params = params, forceReload = forceReload)
}

