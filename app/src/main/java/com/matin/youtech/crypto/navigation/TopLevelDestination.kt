package com.matin.youtech.crypto.navigation

import androidx.annotation.StringRes
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.screen.main.navigation.MARKET_ROUTE
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
    )
}
