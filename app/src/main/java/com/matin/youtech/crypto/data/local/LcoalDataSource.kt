package com.matin.youtech.crypto.data.local

import com.matin.youtech.crypto.data.model.PortfolioItemEntity
import javax.inject.Inject

interface LocalDataSource {
    fun getPortfolio(): List<PortfolioItemEntity>
    fun savePortfolio(portfolio: List<PortfolioItemEntity>)
}

class LocalDataSourceImpl @Inject constructor() : LocalDataSource {
    override fun getPortfolio(): List<PortfolioItemEntity> {
        TODO()
    }
    override fun savePortfolio(portfolio: List<PortfolioItemEntity>) {
        TODO()
    }
}