package com.matin.youtech.crypto.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TradeItemNetwork(
    override val componentType: ComponentType = ComponentType.TradeRowItemComponent,
    val coinName: String,
    val iconUrl: String,
    val price: String,
    val change: String
) : ComponentNetwork

@Serializable
@SerialName("TradeRowComponent")
data class TradeRowNetwork(
    override val componentType: ComponentType = ComponentType.TradeRowComponent,
    val title: String,
    val children: List<TradeItemNetwork>
) : ComponentNetwork
