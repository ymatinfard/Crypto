package com.matin.youtech.crypto.data.model

import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.data.toDomain
import com.matin.youtech.crypto.domain.model.Modal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ModalComponent")
data class ModalNetwork(
    override val componentType: ComponentType = ComponentType.ModalComponent,
    val screen: ScreenNetwork,
) : ComponentNetwork {
    override fun toDomain(): Component {
        return Modal(screen = screen.toDomain())
    }
}
