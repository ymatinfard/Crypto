package com.matin.youtech.crypto.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.component.BannerPager
import com.matin.youtech.crypto.ui.component.CryptoLoadingWheel
import com.matin.youtech.crypto.ui.component.MainTopBar
import com.matin.youtech.crypto.ui.component.MarketItemRow
import com.matin.youtech.crypto.ui.component.MarketTabAction
import com.matin.youtech.crypto.ui.component.StickyMarketTab
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
            Box(modifier = Modifier.fillMaxSize()) {
                CryptoLoadingWheel(
                    modifier = Modifier.align(Alignment.Center),
                    contentDesc = stringResource(R.string.loading)
                )
            }
        }

        is MainScreenUiState.Success -> {
            MainScreenContent(uiState.data, depositClick, marketTabClick)
        }

        is MainScreenUiState.Error -> {
            // TODO
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(
    uiState: MainScreenState,
    depositClick: () -> Unit,
    marketTabClick: (MarketTabAction) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
    ) {
        MainTopBar()
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
            item {
                MainContent(depositClick)
            }
            stickyHeader {
                StickyMarketTab(marketTabClick)
            }
            items(uiState.marketList) {
                MarketItemRow(it)
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