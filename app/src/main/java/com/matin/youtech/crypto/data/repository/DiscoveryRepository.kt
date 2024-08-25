package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.remote.RemoteDataSource
import javax.inject.Inject

class DiscoveryRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
}