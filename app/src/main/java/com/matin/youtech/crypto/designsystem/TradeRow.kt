package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.data.model.ComponentType
import com.matin.youtech.crypto.data.model.TradeItemNetwork


@Composable
fun TradeRow(
    modifier: Modifier = Modifier,
    title: String? = null,
    list: List<TradeItemNetwork>
) {
    Column {
        RowTitle(title = title)
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(list) {
                TradeRowItem(item = it)
            }
        }
    }
}

@Composable
fun TradeRowItem(
    item: TradeItemNetwork = TradeItemNetwork(
        componentType = ComponentType.TradeRowItemComponent,
        coinName = "BTC",
        iconUrl = "icon_url",
        price = "345.123",
        change = "1.2%"
    )
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(120.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.coinName)
                Icon(
                    modifier = Modifier.size(20.dp),
                    //  painter = rememberAsyncImagePainter(model = item.iconUrl),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column {
                Text(
                    text = item.change.addPriceChangeSign(),
                    color = pickPriceChangeColor(item.change)
                )
                Text(
                    text = "$${item.price}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun RowTitle(title: String?, isPro: Boolean = true) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        androidx.compose.material3.Text(text = title ?: "")
        if (isPro) {
            Box(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 2.dp)

            )
            {
                androidx.compose.material3.Text("Pro", fontSize = 10.sp)
            }
        }
    }
}