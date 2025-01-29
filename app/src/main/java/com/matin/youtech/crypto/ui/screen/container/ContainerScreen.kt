package com.matin.youtech.crypto.ui.screen.container

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.sdui.UIRenderer
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.discover.ActionListener

@Composable
fun ContainerScreenRoute(appState: CryptoAppState, viewModel: ContainerViewModel, action: ActionListener?) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ContainerScreen(uiState.value, appState, action)
}

@Composable
fun ContainerScreen(uiState: ContainerUiState, appState: CryptoAppState, action: ActionListener?) {
    when (uiState) {
        is ContainerUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ContainerUiState.Success -> {
            UIRenderer().Render(uiState.data, appState, action)
        }

        is ContainerUiState.Error -> {
            Text("Error: ${uiState.exception.message}")
        }
    }
}
