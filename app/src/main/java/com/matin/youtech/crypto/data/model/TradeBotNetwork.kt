package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Component
import com.matin.youtech.crypto.domain.model.TradeBot
import kotlinx.serialization.Serializable

@Serializable
data class TradeBotNetwork(
    override val componentType: ComponentType = ComponentType.TradeBotComponent,
    val name: String,
    val iconUrl: String,
    val roi: String,
    val minInvestment: String,
    val runTime: String,
    val copies: Int,
) : ComponentNetwork {
    override fun toDomain(): Component {
        return TradeBot(
            name = name,
            iconUrl = iconUrl,
            roi = roi,
            minInvestment = minInvestment,
            runTime = runTime,
            copies = copies
        )
    }
}
