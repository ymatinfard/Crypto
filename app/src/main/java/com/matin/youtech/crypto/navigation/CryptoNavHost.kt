package com.matin.youtech.crypto.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.matin.youtech.crypto.sdui.Action
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.container.navigation.CONTAINER_ROUTE
import com.matin.youtech.crypto.ui.screen.container.navigation.containerScreen
import com.matin.youtech.crypto.ui.screen.discover.navigation.discoverScreen
import com.matin.youtech.crypto.ui.screen.market.navigation.MARKET_ROUTE
import com.matin.youtech.crypto.ui.screen.market.navigation.marketScreen
import com.matin.youtech.crypto.ui.screen.portfolio.navigation.portfolioScreen

@Composable
fun CryptoNavHost(modifier: Modifier, appState: CryptoAppState) {
    NavHost(modifier = modifier, navController = appState.navController, startDestination = MARKET_ROUTE) {
        marketScreen()
        portfolioScreen()
        discoverScreen(appState = appState, action = {
            when (it) {
                is Action.Navigation -> {
                    appState.navController.navigate(CONTAINER_ROUTE + "/${it.destination}")
                }

                else -> {}
            }
        })
        containerScreen(appState = appState, action = {})
    }
}