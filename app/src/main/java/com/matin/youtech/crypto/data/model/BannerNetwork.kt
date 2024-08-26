package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Banner
import com.matin.youtech.crypto.domain.model.Component
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BannerComponent")
data class BannerNetwork(
    override val componentType: ComponentType = ComponentType.BannerComponent,
    val title: String,
    val description: List<String>,
    val iconUrl: String,
) : ComponentNetwork {
    override fun toDomain(): Component {
        return Banner(title, description, iconUrl)
    }
}
