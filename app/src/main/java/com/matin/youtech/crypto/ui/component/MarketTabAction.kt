package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R

@Composable
fun MarketTab(modifier: Modifier = Modifier, selectedTab: (MarketTabAction) -> Unit = {}) {
    var selectedAction by remember { mutableStateOf<MarketTabAction>(MarketTabAction.Hot) }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(MarketTabItemList) { tabItem ->
            MarketTabItem(
                modifier = Modifier.padding(horizontal = 2.dp),
                item = tabItem,
                isSelected = tabItem.action == selectedAction,
            ) { action ->
                selectedAction = action
                selectedTab(action)
            }
        }
    }
}

@Composable
fun MarketTabItem(
    modifier: Modifier,
    item: TabItem,
    isSelected: Boolean = false,
    onClick: (MarketTabAction) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = if (isSelected) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent)
            .padding(horizontal = 8.dp)
            .height(24.dp)
            .clickable { onClick(item.action) },
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.name)
            if (item.type == Item.Switch) {
                Column(modifier = Modifier.padding(start = 4.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_up),
                        contentDescription = "increase",
                        modifier = Modifier.size(12.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow_down),
                        contentDescription = "decrease",
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

    }
}

sealed interface MarketTabAction {
    data object Hot : MarketTabAction
    data object MarketCap : MarketTabAction
    data object Price : MarketTabAction
    data object TwentyFourChange : MarketTabAction
}

val MarketTabItemList = listOf(
    TabItem("Hot", Item.Simple, MarketTabAction.Hot),
    TabItem("Market Cap", Item.Simple, MarketTabAction.MarketCap),
    TabItem("Price", Item.Switch, MarketTabAction.Price),
    TabItem("24H Changes", Item.Switch, MarketTabAction.TwentyFourChange),
)

data class TabItem(val name: String, val type: Item, val action: MarketTabAction)

enum class Item {
    Switch,
    Simple
}