package com.matin.youtech.crypto.ui.screen.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.data.repository.MarketRepository
import com.matin.youtech.crypto.domain.model.MarketItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketScreenViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {
    var uiState = MutableStateFlow<MarketScreenUiState>(MarketScreenUiState.Loading)
        private set

    init {
        getMarketList()
    }

    private fun getMarketList() {
        viewModelScope.launch {
            marketRepository.getMarketList().collect {
                println("getMarketList $it")
                uiState.value = MarketScreenUiState.Success(MainScreenState(it))
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