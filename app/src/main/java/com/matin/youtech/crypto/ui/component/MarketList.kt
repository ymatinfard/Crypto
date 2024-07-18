package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.domain.MarketListItem
import com.matin.youtech.crypto.ui.theme.Green40
import com.matin.youtech.crypto.ui.theme.Red45

@Composable
fun MarketList(marketList: List<MarketListItem>) {
    LazyColumn (contentPadding = PaddingValues(vertical = 8.dp)){
        items(marketList) {
            MarketItemRow(it)
        }
    }
}

@Preview
@Composable
fun MarketItemRow(item: MarketListItem = MarketListItem()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { /*TODO*/ },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CryptoImageLoader(
                url = item.coinUrl,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.coinName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.ticker,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = item.priceChange,
                color = pickPriceChangeColor(item.priceChange),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.price.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun pickPriceChangeColor(priceChange: String) =
    if (priceChange.dropLast(1).toInt() >= 0) Green40 else Red45