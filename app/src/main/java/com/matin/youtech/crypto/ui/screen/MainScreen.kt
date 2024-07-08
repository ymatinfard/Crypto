package com.matin.youtech.crypto.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.ui.component.BannerPager
import com.matin.youtech.crypto.ui.component.MainTopBar
import com.matin.youtech.crypto.ui.component.TotalBalance
import com.matin.youtech.crypto.ui.theme.CryptoTheme

@Composable
fun MainScreen(modifier: Modifier, depositClick: () -> Unit = {}) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        MainTopBar()
        TotalBalance(modifier = Modifier.padding(vertical = 8.dp), depositClick)
        BannerPager()
    }
}

@Preview
@Composable
fun CryptoMainTopBarPreview() {
    CryptoTheme {
        MainScreen(modifier = Modifier)
    }
}