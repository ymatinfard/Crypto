package com.matin.youtech.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ScreenNetwork(val title: String, val components: List<ComponentNetwork>)