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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.domain.model.TradeItem
import com.matin.youtech.crypto.domain.model.TradeRow


@Composable
fun TradeRow(
    tradeRow: TradeRow
) {
    val screenWith = LocalConfiguration.current.screenWidthDp.dp
    Column {
        RowTitle(title = tradeRow.title)
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(tradeRow.children) {
                TradeRowItem(modifier = Modifier.width(screenWith / 2 - 12.dp), item = it)
            }
        }
    }
}

@Composable
fun TradeRowItem(
    modifier: Modifier = Modifier,
    item: TradeItem = TradeItem(
        coinName = "BTC",
        iconUrl = "icon_url",
        price = "345.123",
        change = "1.2%"
    )
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
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
            Spacer(modifier = Modifier.height(20.dp))
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
            )
            {
                Text("Pro", fontSize = 10.sp, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}