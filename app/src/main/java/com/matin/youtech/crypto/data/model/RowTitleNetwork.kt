package com.matin.youtech.crypto.data.model

import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.domain.model.RowTitle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("RowTitleComponent")
@Serializable
data class RowTitleNetwork(
    override val componentType: ComponentType = ComponentType.RowTitleComponent,
    val title: String,
    val badge: String? = null
) : ComponentNetwork {
    override fun toDomain(): Component {
        return RowTitle(
            title = title,
            badge = badge
        )
    }
}
