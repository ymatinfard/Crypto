package com.matin.youtech.crypto.ui.screen.container

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.data.repository.SDUIRepository
import com.matin.youtech.crypto.domain.model.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: SDUIRepository
) : ViewModel() {
    var uiState: MutableStateFlow<ContainerUiState> = MutableStateFlow(ContainerUiState.Loading)
        private set

    private val id: String? = savedStateHandle["id"]

    init {
        fetchScreen()
        Log.d("ContainerViewModel", "Received Id: $id")
    }

    private fun fetchScreen() {
        viewModelScope.launch {
            uiState.value = ContainerUiState.Loading
            if (id != null) {
                uiState.update { ContainerUiState.Success(repository.getScreen(id)) }
            }
        }
    }
}

sealed class ContainerUiState {
    data object Loading : ContainerUiState()
    data class Success(val data: Screen) : ContainerUiState()
    data class Error(val exception: Exception) : ContainerUiState()
}
