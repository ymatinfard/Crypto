package com.matin.youtech.crypto.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.component.BannerPager
import com.matin.youtech.crypto.ui.component.CryptoLoadingWheel
import com.matin.youtech.crypto.ui.component.MarketItemRow
import com.matin.youtech.crypto.ui.component.MarketTabAction
import com.matin.youtech.crypto.ui.component.MarketTopBar
import com.matin.youtech.crypto.ui.component.StickyMarketTab
import com.matin.youtech.crypto.ui.component.TotalBalance

@Composable
fun MarketScreenRoute(viewModel: MarketScreenViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MarketScreen(
        uiState,
        depositClick = { viewModel.intentToAction(MainScreenIntent.DepositClick) },
        marketTabClick = { action -> viewModel.intentToAction(MainScreenIntent.MarketTabClick(action)) }
    )
}

@Composable
internal fun MarketScreen(
    uiState: MarketScreenUiState,
    depositClick: () -> Unit,
    marketTabClick: (MarketTabAction) -> Unit
) {
    when (uiState) {
        is MarketScreenUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CryptoLoadingWheel(
                    modifier = Modifier.align(Alignment.Center),
                    contentDesc = stringResource(R.string.loading)
                )
            }
        }

        is MarketScreenUiState.Success -> {
            MarketScreenContent(uiState.data, depositClick, marketTabClick)
        }

        is MarketScreenUiState.Error -> {
            // TODO
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarketScreenContent(
    uiState: MainScreenState,
    depositClick: () -> Unit,
    marketTabClick: (MarketTabAction) -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = padding.calculateTopPadding(),
                )
        ) {
            MarketTopBar()
            LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                item {
                    MainContent(depositClick)
                }
                stickyHeader {
                    StickyMarketTab(marketTabClick)
                }
                items(uiState.marketList) {
                    MarketItemRow(item = it)
                }
            }
        }
    }
}

@Composable
private fun MainContent(depositClick: () -> Unit) {
    TotalBalance(modifier = Modifier.padding(vertical = 8.dp)) { depositClick() }
    Spacer(modifier = Modifier.height(16.dp))
    BannerPager()
}

interface MainScreenIntent {
    data object DepositClick : MainScreenIntent
    data class MarketTabClick(val action: MarketTabAction) : MainScreenIntent
}