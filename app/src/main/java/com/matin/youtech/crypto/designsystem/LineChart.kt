package com.matin.youtech.crypto.designsystem

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.annotaions.ComponentRenderer
import com.matin.youtech.crypto.domain.model.LineChart
import com.matin.youtech.crypto.sdui.UIComponent
import com.matin.youtech.crypto.ui.screen.discover.ActionListener
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@ComponentRenderer(dataComponent = LineChart::class)
class LineChartComponent : UIComponent<LineChart> {

    @Composable
    override fun BuildUI(data: LineChart, action: ActionListener?) {
        Chart(data)
    }

    @Composable
    fun Chart(data: LineChart) {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 22.dp, vertical = 22.dp),
            data = remember {
                listOf(
                    Line(
                        label = data.label,
                        values = data.points,
                        color = SolidColor(Color(0xFF23af92)),
                        firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                        secondGradientFillColor = Color.Transparent,
                        strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                        gradientAnimationDelay = 1000,
                        drawStyle = DrawStyle.Stroke(width = 2.dp),
                    )
                )
            },
            animationMode = AnimationMode.Together(delayBuilder = {
                it   * 500L
            }),
        )
    }
}

@Preview
@Composable
fun LineChartPreview() {
    val chart = LineChartComponent()
    chart.BuildUI(LineChart("BTC", points = listOf(28.0, 41.0, 5.0, 10.0, 35.0)), action = {})
}