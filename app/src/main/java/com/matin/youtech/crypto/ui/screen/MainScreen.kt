package com.matin.youtech.crypto.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.ui.component.BannerPager
import com.matin.youtech.crypto.ui.component.MainTopBar
import com.matin.youtech.crypto.ui.component.MarketTab
import com.matin.youtech.crypto.ui.component.MarketTabAction
import com.matin.youtech.crypto.ui.component.TotalBalance
import com.matin.youtech.crypto.ui.theme.CryptoTheme

@Composable
fun MainScreen(modifier: Modifier, viewModel: MainScreenViewModel = MainScreenViewModel()) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.onSurface)
            .padding(horizontal = 8.dp)
    ) {
        MainTopBar()
        TotalBalance(modifier = Modifier.padding(vertical = 8.dp)) {
            viewModel.intentToAction(MainScreenIntent.DepositClick)
        }
        Spacer(modifier = Modifier.height(16.dp))
        BannerPager()
        MarketTab(modifier = Modifier.padding(top = 16.dp)) { action ->
            viewModel.intentToAction(MainScreenIntent.MarketTabClick(action))
        }
    }
}

@Preview
@Composable
fun CryptoMainScreenPreview() {
    CryptoTheme(darkTheme = false) {
        MainScreen(modifier = Modifier)
    }
}

interface MainScreenIntent {
    data object DepositClick : MainScreenIntent
    data class MarketTabClick(val action: MarketTabAction) : MainScreenIntent
}