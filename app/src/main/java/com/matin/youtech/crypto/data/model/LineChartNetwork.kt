package com.matin.youtech.crypto.data.model

import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.domain.model.LineChart
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("LineChartComponent")
data class LineChartNetwork(val label: String, val points: List<Double>): ComponentNetwork {
    override val componentType: ComponentType = ComponentType.LineChartComponent
    override fun toDomain(): Component {
        return LineChart(label, points)
    }
}
