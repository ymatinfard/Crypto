package com.matin.youtech.crypto.sdui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.data.repository.SDUIRepository
import com.matin.youtech.crypto.domain.model.CryptoCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCoinRefreshingViewModel @Inject constructor(private val repository: SDUIRepository) :
    ViewModel() {

    private var coinName = MutableStateFlow<String>("BTC")

    @OptIn(ExperimentalCoroutinesApi::class)
    val coinData: StateFlow<CryptoCoin?> = coinName.flatMapLatest { coinName ->
        repository.cryptoCoinPrice
            .map { list -> list.firstOrNull { it.name == coinName } }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CryptoCoin("BTC", 0.0, 0.0, 0.0)
        )

    fun setCoinName(name: String) {
        viewModelScope.launch {
            coinName.emit(name)
        }
    }
}