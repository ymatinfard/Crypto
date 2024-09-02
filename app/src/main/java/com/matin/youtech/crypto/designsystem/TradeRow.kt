package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matin.youtech.annotaions.ComponentRenderer
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.domain.model.TradeItem
import com.matin.youtech.crypto.domain.model.TradeRow
import com.matin.youtech.crypto.sdui.UIComponent

@ComponentRenderer(component = TradeRow::class)
class TradeRowComponent : UIComponent<TradeRow> {

    @Composable
    override fun BuildUI(data: TradeRow) {
        TradeRow(tradeRow = data)
    }

    @Composable
    fun TradeRow(
        tradeRow: TradeRow
    ) {
        val screenWith = LocalConfiguration.current.screenWidthDp.dp
        val itemPadding = 6.dp
        Column {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(itemPadding),
                modifier = Modifier.padding(start = SCREEN_SIDE_PADDING.dp)
            ) {
                items(tradeRow.children) {
                    TradeRowItem(
                        modifier = Modifier.width(screenWith / 2 - (itemPadding * 2) - SCREEN_SIDE_PADDING.dp),
                        item = it
                    )
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
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
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
}