package com.matin.youtech.crypto.ui.screen.container

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.sdui.UIRenderer
import com.matin.youtech.crypto.ui.screen.discover.ActionListener

@Composable
fun ContainerScreenRoute(viewModel: ContainerViewModel, action: ActionListener?) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ContainerScreen(uiState.value, action)
}

@Composable
fun ContainerScreen(uiState: ContainerUiState, action: ActionListener?) {
    when (uiState) {
        is ContainerUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ContainerUiState.Success -> {
            UIRenderer().Render(uiState.data, action)
        }

        is ContainerUiState.Error -> {
            Text("Error: ${uiState.exception.message}")
        }
    }
}
