package com.matin.youtech.crypto.ui.screen.discover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.sdui.UIRenderer
import com.matin.youtech.crypto.ui.component.CryptoLoadingWheel

@Composable
fun DiscoverScreenRoute(viewModel: DiscoverScreenViewModel) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    DiscoverScreen(uiState.value)
}

@Composable
fun DiscoverScreen(uiState: DiscoveryScreenUIState) {
    when (uiState) {
        is DiscoveryScreenUIState.Success -> {
            DiscoverScreenContent(screen = uiState.data)
        }

        is DiscoveryScreenUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CryptoLoadingWheel(
                    modifier = Modifier.align(Alignment.Center),
                    contentDesc = stringResource(R.string.loading)
                )
            }
        }

        is DiscoveryScreenUIState.Error -> {
            Text("Error ${uiState.exception?.message}")
        }
    }
}

@Composable
fun DiscoverScreenContent(screen: Screen) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        UIRenderer.render(screen)
    }
}
