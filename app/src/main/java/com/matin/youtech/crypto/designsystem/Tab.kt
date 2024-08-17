package com.matin.youtech.crypto.designsystem

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ClickableTabs(modifier: Modifier = Modifier, selectedItem: Int, tabsList: List<String>, onClick: (Int) -> Unit) {
    val selectedItemIndex = remember {
        mutableIntStateOf(selectedItem)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface, RoundedCornerShape(70.dp))
            .border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.background
                ), RoundedCornerShape(70.dp)
            )
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabsList.forEachIndexed { index, s ->
                TabItem(
                    isSelected = index == selectedItemIndex.intValue,
                    text = s,
                    Modifier.weight(0.5f)
                ) {
                    selectedItemIndex.intValue = index
                    onClick.invoke(selectedItemIndex.intValue)
                }
            }
        }
    }
}

@Composable
fun TabItem(isSelected: Boolean, text: String, modifier: Modifier, onClick: () -> Unit) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            Color.Black
        },
        animationSpec = tween(easing = LinearEasing), label = ""
    )

    val background: Color by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.inverseOnSurface,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing), label = ""
    )
    val border = if (isSelected)
        BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.background
        )
    else
        BorderStroke(
            0.dp,
            Color.White
        )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(background, RoundedCornerShape(70.dp))
            .border(
                border = border, RoundedCornerShape(70.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {

                onClick.invoke()
            }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            color = tabTextColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Tabs() {
    Column(modifier = Modifier.padding(20.dp)) {
        ClickableTabs(modifier = Modifier, selectedItem = 0, tabsList = listOf("List Item1", "List Item2"), onClick = {})
    }
}