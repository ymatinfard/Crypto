package com.matin.youtech.crypto.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.data.MarketRepository
import com.matin.youtech.crypto.domain.MarketListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {
    var uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
        private set

    init {
        getMarketList()
    }

    private fun getMarketList() {
        viewModelScope.launch {
            marketRepository.getMarketList().collect {
                println("getMarketList $it")
                uiState.value = MainScreenUiState.Success(MainScreenState(it))
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

sealed class MainScreenUiState {
    data object Loading : MainScreenUiState()
    data class Success(val data: MainScreenState) : MainScreenUiState()
    data class Error(val exception: Exception) : MainScreenUiState()
}

data class MainScreenState(
    val marketList: List<MarketListItem> = emptyList()
)