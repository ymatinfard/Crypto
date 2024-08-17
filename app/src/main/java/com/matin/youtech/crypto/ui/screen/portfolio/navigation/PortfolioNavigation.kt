package com.matin.youtech.crypto.ui.screen.portfolio.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.youtech.crypto.ui.screen.portfolio.PortfolioScreenRoute
import com.matin.youtech.crypto.ui.screen.portfolio.PortfolioScreenViewModel

const val PORTFOLIO_ROUTE = "portfolio"

fun NavGraphBuilder.portfolioScreen() {
    composable(PORTFOLIO_ROUTE) {
        PortfolioScreenRoute(viewModel = hiltViewModel<PortfolioScreenViewModel>())
    }
}