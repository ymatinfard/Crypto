package com.matin.youtech.crypto.ui.screen.container

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.sdui.UIRenderer

@Composable
fun ContainerScreenRoute(viewModel: ContainerViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ContainerScreen(uiState.value)
}

@Composable
fun ContainerScreen(uiState: ContainerUiState) {
    when (uiState) {
        is ContainerUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ContainerUiState.Success -> {
            UIRenderer().Render(uiState.data)
        }

        is ContainerUiState.Error -> {
            Text("Error: ${uiState.exception.message}")
        }
    }
}
