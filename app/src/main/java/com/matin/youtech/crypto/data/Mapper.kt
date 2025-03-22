package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.ActionNetwork
import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.ScreenNetwork
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.sdui.Action

fun List<MarketItemNetwork>.toDomain() = map { it.toDomain() }

fun ScreenNetwork.toDomain() = Screen(title, components.map { it.toDomain() })

// TODO() remove this extension function
internal fun ActionNetwork.toAction(): Action = when (this.type) {
    "navigation" -> Action.Navigation(destination = this.data)
    // Todo() Modal data may be included in screen data! Let's see how it works
    "showModal" -> Action.ShowModal(destination = this.data)
    "noOperation" -> Action.NoOperation
    else -> {
        Action.NoOperation
    }
}