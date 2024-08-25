package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Component
import com.matin.youtech.crypto.domain.model.TradeItem
import com.matin.youtech.crypto.domain.model.TradeRow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TradeItemNetwork(
    override val componentType: ComponentType = ComponentType.TradeRowItemComponent,
    val coinName: String,
    val iconUrl: String,
    val price: String,
    val change: String
) : ComponentNetwork {
    override fun toDomain(): Component {
        return TradeItem(
            coinName = coinName,
            iconUrl = iconUrl,
            price = price,
            change = change
        )
    }
}

@Serializable
@SerialName("TradeRowComponent")
data class TradeRowNetwork(
    override val componentType: ComponentType = ComponentType.TradeRowComponent,
    val title: String,
    val children: List<TradeItemNetwork>
) : ComponentNetwork {
    override fun toDomain(): Component {
        return TradeRow(
            title = title,
            children = children.map { it.toDomain() as TradeItem }
        )
    }
}
