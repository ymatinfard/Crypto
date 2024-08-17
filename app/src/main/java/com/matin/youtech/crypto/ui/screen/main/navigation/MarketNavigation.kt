package com.matin.youtech.crypto.ui.screen.main.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.youtech.crypto.ui.screen.main.MarketScreenRoute
import com.matin.youtech.crypto.ui.screen.main.MarketScreenViewModel

const val MARKET_ROUTE = "market"

fun NavGraphBuilder.marketScreen() {
    return composable(MARKET_ROUTE) {
        MarketScreenRoute(viewModel = hiltViewModel<MarketScreenViewModel>())
    }
}