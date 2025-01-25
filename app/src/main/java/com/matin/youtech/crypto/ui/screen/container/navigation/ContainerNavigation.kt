package com.matin.youtech.crypto.ui.screen.container.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.youtech.crypto.ui.screen.container.ContainerScreenRoute
import com.matin.youtech.crypto.ui.screen.container.ContainerViewModel

const val CONTAINER_ROUTE = "container"

fun NavGraphBuilder.containerScreen() {
    return composable(CONTAINER_ROUTE) {
        ContainerScreenRoute(viewModel = hiltViewModel<ContainerViewModel>())
    }
}