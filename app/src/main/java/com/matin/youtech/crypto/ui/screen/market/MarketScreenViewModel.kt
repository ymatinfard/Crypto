package com.matin.youtech.crypto.ui.screen.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.domain.MarketRepository
import com.matin.youtech.crypto.domain.model.MarketItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketScreenViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {
    private var _uiState = MutableStateFlow<MarketScreenUiState>(MarketScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getMarketList()
    }

    private fun getMarketList() {
        viewModelScope.launch {
            marketRepository.getMarketList().collect { marketList ->
                _uiState.update {
                    MarketScreenUiState.Success(MainScreenState(marketList))
                }

            }
        }
    }

    fun intentToAction(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.DepositClick -> {
                // TODO
                println("Deposit clicked")
            }

            is MainScreenIntent.MarketTabClick -> {
                //Todo
                println("MarketTab clicked $$intent")
            }
        }
    }
}

sealed class MarketScreenUiState {
    data object Loading : MarketScreenUiState()
    data class Success(val data: MainScreenState) : MarketScreenUiState()
    data class Error(val exception: Exception) : MarketScreenUiState()
}

data class MainScreenState(
    val marketList: List<MarketItem> = emptyList()
)