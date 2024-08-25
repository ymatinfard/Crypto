package com.matin.youtech.crypto.di

import com.matin.youtech.crypto.data.repository.MarketRepository
import com.matin.youtech.crypto.data.repository.MarketRepositoryImpl
import com.matin.youtech.crypto.data.repository.PortfolioRepository
import com.matin.youtech.crypto.data.repository.PortfolioRepositoryImpl
import com.matin.youtech.crypto.data.local.LocalDataSource
import com.matin.youtech.crypto.data.local.LocalDataSourceImpl
import com.matin.youtech.crypto.data.remote.RemoteDataSource
import com.matin.youtech.crypto.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModules {

    @Binds
    @Singleton
    abstract fun bindsMarketRepository(marketRepositoryImpl: MarketRepositoryImpl): MarketRepository

    @Binds
    @Singleton
    abstract fun bindsPortfolioRepository(portfolioRepositoryImpl: PortfolioRepositoryImpl): PortfolioRepository

    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object AppModuleProvider {

    @Provides
    fun coroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)
}