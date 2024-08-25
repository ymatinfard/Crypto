package com.matin.youtech.crypto.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BrandComponent")
data class BrandNetwork(
    override val componentType: ComponentType = ComponentType.BrandComponent,
    val name: String,
    val image: String
) : ComponentNetwork
