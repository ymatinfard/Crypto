package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Brand
import com.matin.youtech.crypto.domain.model.Component
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BrandComponent")
data class BrandNetwork(
    override val componentType: ComponentType = ComponentType.BrandComponent,
    val name: String,
    val image: String
) : ComponentNetwork {
    override fun toDomain(): Component {
        return Brand(name = name, image = image)
    }
}
