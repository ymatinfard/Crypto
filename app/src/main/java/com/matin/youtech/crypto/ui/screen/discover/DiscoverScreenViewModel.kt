package com.matin.youtech.crypto.ui.screen.discover

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
class DiscoverScreenViewModel @Inject constructor(private val SDUIRepository: SDUIRepository) :
    ViewModel() {

    var uiState =
        MutableStateFlow<DiscoveryScreenUIState>(DiscoveryScreenUIState.Loading)
        private set

    init {
        getDiscoveryScreen()
    }

    private fun getDiscoveryScreen() {
        viewModelScope.launch {
            uiState.update {
                DiscoveryScreenUIState.Success(SDUIRepository.getDiscoveryScreen())
            }
        }
    }
}

sealed interface DiscoveryScreenUIState {
    data class Success(val data: Screen) : DiscoveryScreenUIState
    data class Error(val exception: Throwable?) : DiscoveryScreenUIState
    data object Loading : DiscoveryScreenUIState
}