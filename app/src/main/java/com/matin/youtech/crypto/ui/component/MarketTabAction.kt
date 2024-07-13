package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R

@Preview
@Composable
fun MarketTab() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    LazyRow(contentPadding = PaddingValues(horizontal = 4.dp)) {
        items(MarketTabItemList) {
            MarketTabItem(
                name = it.name,
                type = it.type,
                isSelected = (selectedIndex == MarketTabItemList.indexOf(it)),
                modifier = Modifier.padding(horizontal = 2.dp)
            ) {
                selectedIndex = MarketTabItemList.indexOf(it)
            }
        }
    }
}

@Composable
fun MarketTabItem(
    modifier: Modifier,
    name: String,
    type: ItemType,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent)
            .padding(horizontal = 6.dp)
            .height(24.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = name)
            if (type == ItemType.Switch) {
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

//sealed class MarketTabAction(val name: String, type: ItemType) {
//    data class Hot(val hotName: String, val type: ItemType = ItemType.Simple) : MarketTabAction(hotName, type)
//    data class MarketCap(val marketCapName: String) : MarketTabAction(marketCapName)
//    data class Price(val priceName: String) : MarketTabAction(priceName)
//    data class TwentyFourChange(val twentyFourChangeName: String) :
//        MarketTabAction(twentyFourChangeName)
//}
//
val MarketTabItemList = listOf(
    TabItem("Hot", ItemType.Simple),
    TabItem("MarketCap", ItemType.Simple),
    TabItem("Price", ItemType.Switch),
    TabItem("24Changes", ItemType.Switch),
)

data class TabItem(val name: String, val type: ItemType)

enum class ItemType {
    Switch,
    Simple
}