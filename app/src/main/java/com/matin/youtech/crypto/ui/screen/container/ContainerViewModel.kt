package com.matin.youtech.crypto.ui.screen.container

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.matin.youtech.crypto.domain.model.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow(ContainerUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val id: String? = savedStateHandle["id"]

    init {
        Log.d("ContainerViewModel", "Received Id: $id")
    }
}

sealed class ContainerUiState {
    data object Loading : ContainerUiState()
    data class Success(val data: Screen) : ContainerUiState()
    data class Error(val exception: Exception) : ContainerUiState()
}
