package com.matin.youtech.crypto.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.matin.youtech.crypto.ui.screen.discover.navigation.discoverScreen
import com.matin.youtech.crypto.ui.screen.market.navigation.MARKET_ROUTE
import com.matin.youtech.crypto.ui.screen.market.navigation.marketScreen
import com.matin.youtech.crypto.ui.screen.portfolio.navigation.portfolioScreen

@Composable
fun CryptoNavHost(modifier: Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = MARKET_ROUTE) {
        marketScreen()
        portfolioScreen()
        discoverScreen()
    }
}