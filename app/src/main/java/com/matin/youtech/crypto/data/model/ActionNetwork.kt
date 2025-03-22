package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.sdui.Action
import kotlinx.serialization.Serializable

@Serializable
data class ActionNetwork(
    val type: String,
    val data: String
) {
    fun toDomain(): Action {
        return when (type) {
            "navigation" -> Action.Navigation(data)
            "showModal" -> Action.ShowModal(data)
            else -> Action.NoOperation
        }
    }
}