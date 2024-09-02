package com.matin.youtech.crypto.ui.screen.discover.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.youtech.crypto.ui.screen.discover.DiscoverScreenRoute
import com.matin.youtech.crypto.ui.screen.discover.DiscoverScreenViewModel

const val DISCOVER_ROUTE = "discover"

fun NavGraphBuilder.discoverScreen() {
    return composable(DISCOVER_ROUTE) {
        DiscoverScreenRoute(viewModel = hiltViewModel<DiscoverScreenViewModel>())
    }
}