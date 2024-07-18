package com.matin.youtech.crypto.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.ui.component.BannerPager
import com.matin.youtech.crypto.ui.component.MainTopBar
import com.matin.youtech.crypto.ui.component.MarketList
import com.matin.youtech.crypto.ui.component.MarketTab
import com.matin.youtech.crypto.ui.component.MarketTabAction
import com.matin.youtech.crypto.ui.component.TotalBalance
import com.matin.youtech.crypto.ui.theme.CryptoTheme

@Composable
fun MainScreenRoute(viewModel: MainScreenViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MainScreen(
        uiState,
        depositClick = { viewModel.intentToAction(MainScreenIntent.DepositClick) },
        marketTabClick = { action -> viewModel.intentToAction(MainScreenIntent.MarketTabClick(action)) }
    )
}

@Composable
internal fun MainScreen(
    uiState: MainScreenUiState,
    depositClick: () -> Unit,
    marketTabClick: (MarketTabAction) -> Unit
) {
    when (uiState) {
        is MainScreenUiState.Loading -> {
            // TODO
        }

        is MainScreenUiState.Success -> {
            MainScreenContent(uiState.data, depositClick, marketTabClick)
        }

        is MainScreenUiState.Error -> {
            // TODO
        }
    }
}

@Composable
fun MainScreenContent(
    uiState: MainScreenState,
    depositClick: () -> Unit,
    marketTabClick: (MarketTabAction) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp)
    ) {
        MainTopBar()
        TotalBalance(modifier = Modifier.padding(vertical = 8.dp)) { depositClick() }
        Spacer(modifier = Modifier.height(16.dp))
        BannerPager()
        MarketTab(modifier = Modifier.padding(top = 16.dp)) { action ->
            marketTabClick(action)
        }
        MarketList(marketList = uiState.marketList)
    }
}

@Preview
@Composable
fun CryptoMainScreenPreview() {
    CryptoTheme(darkTheme = false) {
        // MainScreen()
    }
}

interface MainScreenIntent {
    data object DepositClick : MainScreenIntent
    data class MarketTabClick(val action: MarketTabAction) : MainScreenIntent
}