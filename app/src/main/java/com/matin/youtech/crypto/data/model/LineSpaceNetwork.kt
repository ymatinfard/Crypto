package com.matin.youtech.crypto.data.model

import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.domain.model.LineSpace
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("LineSpaceComponent")
@Serializable
data class LineSpaceNetwork(
    override val componentType: ComponentType = ComponentType.LineSpaceComponent,
    val lineCount: Int
) : ComponentNetwork {
    override fun toDomain(): Component {
        return LineSpace(lineCount)
    }
}