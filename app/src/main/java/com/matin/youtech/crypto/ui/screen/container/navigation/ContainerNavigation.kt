package com.matin.youtech.crypto.ui.screen.container.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.container.ContainerScreenRoute
import com.matin.youtech.crypto.ui.screen.container.ContainerViewModel
import com.matin.youtech.crypto.ui.screen.discover.ActionListener

const val CONTAINER_ROUTE = "container"

fun NavGraphBuilder.containerScreen(appState: CryptoAppState, action: ActionListener?) {
    return composable(
        "$CONTAINER_ROUTE/{id}",
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
        ContainerScreenRoute(appState, viewModel = hiltViewModel<ContainerViewModel>(), action)
    }
}