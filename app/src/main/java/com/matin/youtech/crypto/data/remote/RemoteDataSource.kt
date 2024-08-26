package com.matin.youtech.crypto.data.remote

import com.matin.youtech.crypto.data.model.BannerNetwork
import com.matin.youtech.crypto.data.model.ComponentNetwork
import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.PortfolioNetwork
import com.matin.youtech.crypto.data.model.ScreenNetwork
import com.matin.youtech.crypto.data.model.TradeRowNetwork
import com.matin.youtech.crypto.data.repository.fakePortfolio
import com.matin.youtech.crypto.data.repository.getFakeMarketList
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
        val screenJsonString = """{
         "title": "Discovery",
         "components": [
            {"type": "BannerComponent", "title": "Banner title goes here", "description": ["Description line 1", "Description line 2"], "iconUrl": "https://example_ui.com/icon.png"},
            {"type": "TradeRowComponent", "title": "Top Gainers", "children": [
            {"coinName": "BTC","iconUrl": "https://example.com/btc.png","price": "345.123","change": "1.2"},
            {"coinName": "ETH", "iconUrl": "https://example.com/eth.png", "price": "234.567", "change": "0.5"},
            {"coinName": "LTC", "iconUrl": "https://example.com/ltc.png", "price": "123.456", "change": "-0.8"}
            ]}
            ]
        }"""

        val jsonParser = Json {
            ignoreUnknownKeys = true
            serializersModule = SerializersModule {
                polymorphic(ComponentNetwork::class) {
                    subclass(BannerNetwork::class, BannerNetwork.serializer())
                    subclass(TradeRowNetwork::class, TradeRowNetwork.serializer())
                }
            }
        }
        // delay(1000)
        return jsonParser.decodeFromString(screenJsonString)
    }
}