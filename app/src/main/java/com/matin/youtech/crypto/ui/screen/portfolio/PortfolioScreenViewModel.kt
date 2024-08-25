package com.matin.youtech.crypto.ui.screen.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matin.youtech.crypto.data.repository.PortfolioRepository
import com.matin.youtech.crypto.domain.PortfolioParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class PortfolioScreenViewModel @Inject constructor(private val portfolioRepository: PortfolioRepository) :
    ViewModel() {

    fun getPortfolio() =
        portfolioRepository.observePortfolio(PortfolioParameter("123"), true).shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            replay = 1
        )
}