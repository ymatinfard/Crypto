package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matin.youtech.crypto.domain.MarketListItem

@Composable
fun MarketList(marketList: List<MarketListItem>) {
    LazyColumn {
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
            .padding(vertical = 4.dp)
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
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = item.coinName,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = item.ticker,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = item.priceChange,
                color = Color.Green,
                fontSize = 14.sp
            )
            Text(
                text = item.price.toString(),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}