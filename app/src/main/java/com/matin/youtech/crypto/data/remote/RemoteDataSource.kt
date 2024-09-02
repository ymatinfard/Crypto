package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.model.BannerNetwork
import com.matin.youtech.crypto.data.model.ComponentNetwork
import com.matin.youtech.crypto.data.model.LineSpaceNetwork
import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.PortfolioNetwork
import com.matin.youtech.crypto.data.model.RowTitleNetwork
import com.matin.youtech.crypto.data.model.ScreenNetwork
import com.matin.youtech.crypto.data.model.TradeBotNetwork
import com.matin.youtech.crypto.data.model.TradeRowNetwork
import com.matin.youtech.crypto.data.repository.fakePortfolio
import com.matin.youtech.crypto.data.repository.getFakeMarketList
import com.matin.youtech.crypto.data.repository.getFakeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import javax.inject.Inject

interface RemoteDataSource {
    fun getMarketList(): Flow<List<MarketItemNetwork>>
    suspend fun getPortfolio(): PortfolioNetwork
    suspend fun getDiscoveryScreen(): ScreenNetwork
}

class RemoteDataSourceImpl @Inject constructor(private val scope: CoroutineScope) :
    RemoteDataSource {
    val jsonParser = Json {
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            polymorphic(ComponentNetwork::class) {
                subclass(BannerNetwork::class, BannerNetwork.serializer())
                subclass(TradeRowNetwork::class, TradeRowNetwork.serializer())
                subclass(TradeBotNetwork::class, TradeBotNetwork.serializer())
                subclass(RowTitleNetwork::class, RowTitleNetwork.serializer())
                subclass(LineSpaceNetwork::class, LineSpaceNetwork.serializer())
            }
        }
    }

    override fun getMarketList(): Flow<List<MarketItemNetwork>> = flow {
        while (true) {
            delay(500)
            emit(getFakeMarketList())
        }
    }

    override suspend fun getPortfolio(): PortfolioNetwork {
        delay(500)
        return fakePortfolio()
    }

    override suspend fun getDiscoveryScreen(): ScreenNetwork {
        // delay(1000)
        return jsonParser.decodeFromString<ScreenNetwork>(getFakeScreen())
    }
}