package com.matin.youtech.crypto.ui.screen.market.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.youtech.crypto.ui.screen.market.MarketScreenRoute
import com.matin.youtech.crypto.ui.screen.market.MarketScreenViewModel

const val MARKET_ROUTE = "market"

fun NavGraphBuilder.marketScreen() {
    return composable(MARKET_ROUTE) {
        MarketScreenRoute(viewModel = hiltViewModel<MarketScreenViewModel>())
    }
}