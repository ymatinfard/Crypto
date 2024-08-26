package com.matin.youtech.crypto.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.designsystem.theme.LocalAdditionalColors
import com.matin.youtech.crypto.domain.model.TradeBot

@Preview
@Composable
fun TradeBot(
    modifier: Modifier = Modifier, tradeBot: TradeBot = TradeBot(
        name = "TradeBot",
        iconUrl = "https://example.com/icon.png",
        roi = "10%",
        minInvestment = "899.954 USDT",
        runTime = "289d 2h 42m",
        copies = 10
    )
) {
    val additionalColors = LocalAdditionalColors.current
    val isExpanded by remember {
        mutableStateOf(true)
    }

    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null
                )
                Text(text = tradeBot.name)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "ROI", modifier = Modifier.padding(end = 4.dp))
                Text(text = tradeBot.roi, color = additionalColors.positiveRate)
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Card(
                modifier
                    .padding(top = 6.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(18.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BotKeyValue(key = "Min Investment", value = "899.954 USDT")
                        BotKeyValue(key = "Run Time", value = "289d 2h 42m")
                        BotKeyValue(key = "Copies", value = "10")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.End,

                        ) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Create")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BotKeyValue(key: String = "Copies", value: String = "42") {
    Row {
        Text(
            text = key,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(end = 6.dp)
        )
        Text(text = value)
    }
}