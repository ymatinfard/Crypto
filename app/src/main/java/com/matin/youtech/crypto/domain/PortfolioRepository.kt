package com.matin.youtech.crypto.domain

import com.matin.youtech.crypto.data.repository.Data
import com.matin.youtech.crypto.domain.model.Portfolio
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun fetchPortfolioFromServer(): Portfolio
    suspend fun fetchPortfolioFromStorage(): Portfolio
    fun observePortfolio(params: PortfolioParameter, forceReload: Boolean): Flow<Data<Portfolio>>
}