package com.matin.youtech.crypto.data.repository

import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.PortfolioNetwork
import kotlin.random.Random

fun getFakeMarketList() = listOf(
    MarketItemNetwork(
        coinName = "Bitcoin",
        coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png",
        ticker = "BTC",
        priceChange = "${Random.nextInt(-5, 40)}",
        price = Random.nextDouble(from = 40000.0, 60000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DASH",
        coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/dash.png",
        ticker = "DASH",
        priceChange = "${Random.nextInt(-10, 20)}",
        price = Random.nextDouble(from = 10000.0, 40000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DOT",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dot.png?raw=true",
        ticker = "DOT",
        priceChange = "${Random.nextInt(-1, 5)}",
        price = Random.nextDouble(from = 20000.0, 35000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "SOL",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/sol.png?raw=true",
        ticker = "SOL",
        priceChange = "${Random.nextInt(-4, 4)}",
        price = Random.nextDouble(from = 12000.0, 35000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "Link",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/link.png?raw=true",
        ticker = "LINK",
        priceChange = "${Random.nextInt(3, 5)}",
        price = Random.nextDouble(from = 1000.0, 3500.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DOT",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dot.png?raw=true",
        ticker = "DOT",
        priceChange = "${Random.nextInt(-1, 5)}",
        price = Random.nextDouble(from = 20000.0, 35000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "BSD",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/bsd.png?raw=true",
        ticker = "BSD",
        priceChange = "${Random.nextInt(-1, 5)}",
        price = Random.nextDouble(from = 20000.0, 35000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "ANT",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/ant.png?raw=true",
        ticker = "ANT",
        priceChange = "${Random.nextInt(-1, 5)}",
        price = Random.nextDouble(from = 200.0, 460.0).toString()
    ),
    MarketItemNetwork(
        coinName = "ADA",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/ada.png?raw=true",
        ticker = "ADA",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "CALM",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/clam.png?raw=true",
        ticker = "CALM",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DCN",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dcn.png?raw=true",
        ticker = "DCN",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "EDG",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/edg.png?raw=true",
        ticker = "EDG",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DRGN",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/drgn.png?raw=true",
        ticker = "DRGN",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
    MarketItemNetwork(
        coinName = "DLT",
        coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dlt.png?raw=true",
        ticker = "DLT",
        priceChange = "${Random.nextInt(-1, 8)}",
        price = Random.nextDouble(from = 17000.0, 29000.0).toString()
    ),
)

fun fakePortfolio(): PortfolioNetwork {
    val marketItems = listOf(
        MarketItemNetwork(
            coinName = "Bitcoin",
            coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png",
            ticker = "BTC",
            priceChange = "${Random.nextInt(-5, 40)}",
            price = Random.nextDouble(from = 40000.0, 60000.0).toString()
        ),
        MarketItemNetwork(
            coinName = "DASH",
            coinUrl = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/dash.png",
            ticker = "DASH",
            priceChange = "${Random.nextInt(-10, 20)}",
            price = Random.nextDouble(from = 10000.0, 40000.0).toString()
        ),
        MarketItemNetwork(
            coinName = "DOT",
            coinUrl = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/dot.png?raw=true",
            ticker = "DOT",
            priceChange = "${Random.nextInt(-1, 5)}",
            price = Random.nextDouble(from = 20000.0, 35000.0).toString()
        )
    )
    return PortfolioNetwork(id = "123", marketItems = marketItems)
}

fun getFakeScreen() = """{
         "title": "Discovery",
         "components": [
            {"type": "BannerComponent", "title": "Banner title goes here", "description": ["Description line 1", "Description line 2"], "iconUrl": "https://example_ui.com/icon.png"},
            {"type": "RowTitleComponent", "title": "Top Gainers", "badge": "Pro"},
            {"type": "TradeRowComponent", "children": [
            {"coinName": "BTC","iconUrl": "https://example.com/btc.png","price": "345.123","change": "1.2"},
            {"coinName": "ETH", "iconUrl": "https://example.com/eth.png", "price": "234.567", "change": "0.5"},
            {"coinName": "LTC", "iconUrl": "https://example.com/ltc.png", "price": "123.456", "change": "-0.8"},
            {"coinName": "SOL", "iconUrl": "https://example.com/sol.png", "price": "123.456", "change": "-0.8"},
            {"coinName": "LINK", "iconUrl": "https://example.com/link.png", "price": "123.456", "change": "-0.8"}
            ]},
            {"type": "RowTitleComponent", "title": "Top ROI Trading Bots", "badge": "Pro"},
            {"type": "TradeBotComponent", "name": "SUN/USDT", "iconUrl": "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/dash.png", "roi": "123.232", "minInvestment": "899.954 USDT", "runTime": "289d 2h 42m", "copies": 10},
            {"type": "TradeBotComponent", "name": "ICP/USDT", "iconUrl": "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/edg.png?raw=true", "roi": "32.32", "minInvestment": "899.954 USDT", "runTime": "289d 2h 42m", "copies": 22},
            {"type": "TradeBotComponent", "name": "SOL/ETH", "iconUrl": "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png", "roi": "153.78", "minInvestment": "899.954 USDT", "runTime": "289d 2h 42m", "copies": 42},
            {"type": "TradeBotComponent", "name": "ICP/USDT", "iconUrl": "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png", "roi": "197.43", "minInvestment": "899.954 USDT", "runTime": "289d 2h 42m", "copies": 10},
            {"type": "TradeBotComponent", "name": "ICP/USDT", "iconUrl": "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/btc.png", "roi": "197.43", "minInvestment": "899.954 USDT", "runTime": "289d 2h 42m", "copies": 10}
            ]
        }"""