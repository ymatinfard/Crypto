package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.screen.market.MainScreenIntent
import com.matin.youtech.crypto.designsystem.theme.CryptoTheme

@Composable
fun TotalBalance(modifier: Modifier, depositClick: (MainScreenIntent) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.total_balance))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.fake_balance),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Button(
                modifier = Modifier.height(32.dp),
                onClick = { depositClick(MainScreenIntent.DepositClick) },
                contentPadding = PaddingValues(horizontal = 18.dp),
            ) {
                Text(
                    text = stringResource(R.string.deposit),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun TotalBalancePreview() {
    CryptoTheme {
        TotalBalance(modifier = Modifier) {}
    }
}