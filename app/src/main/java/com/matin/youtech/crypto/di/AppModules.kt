package com.matin.youtech.crypto.di

import com.matin.youtech.crypto.data.Repository
import com.matin.youtech.crypto.data.RepositoryImpl
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
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object AppModuleProvider {

    @Provides
    fun coroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)
}