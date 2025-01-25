package com.matin.youtech.crypto.ui.screen.container

import androidx.lifecycle.ViewModel
import com.matin.youtech.crypto.domain.model.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContainerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ContainerUiState.Loading)
    val uiState = _uiState.asStateFlow()
}

sealed class ContainerUiState {
    data object Loading: ContainerUiState()
    data class Success(val data: Screen): ContainerUiState()
    data class Error(val exception: Exception): ContainerUiState()
}
