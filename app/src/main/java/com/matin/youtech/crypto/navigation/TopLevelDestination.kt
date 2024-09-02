package com.matin.youtech.crypto.navigation

import androidx.annotation.StringRes
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.screen.discover.navigation.DISCOVER_ROUTE
import com.matin.youtech.crypto.ui.screen.market.navigation.MARKET_ROUTE
import com.matin.youtech.crypto.ui.screen.portfolio.navigation.PORTFOLIO_ROUTE

enum class TopLevelDestination(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: Int,
) {
    Market(
        MARKET_ROUTE,
        R.string.market,
        R.drawable.ic_market,
    ),
    Portfolio(
        PORTFOLIO_ROUTE,
        R.string.portfolio,
        R.drawable.ic_portfolio,
    ),
    Discover(
        DISCOVER_ROUTE,
        R.string.discover,
        R.drawable.ic_discover,
    ),
    Profile(
        DISCOVER_ROUTE,
        R.string.profile,
        R.drawable.ic_profile,
    )
}
