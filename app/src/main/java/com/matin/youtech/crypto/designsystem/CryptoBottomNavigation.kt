package com.matin.youtech.crypto.designsystem

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.navigation.TopLevelDestination
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun CryptoBottomNavigation(
    modifier: Modifier = Modifier,
    currentDestination: TopLevelDestination = TopLevelDestination.Market,
    noNavigationBarClick: (TopLevelDestination) -> Unit = {},
    bottomNavItems: List<TopLevelDestination> = TopLevelDestination.entries
) {
    val tradeButtonSize = 50f
    var showBottomSheet by remember { mutableStateOf(false) }
    var isTradeBtnClicked by remember { mutableStateOf(false) }

    val animateRotate: Float by animateFloatAsState(if (showBottomSheet) 0f else 45f)
    val animateSize: Float by animateFloatAsState(
        if (isTradeBtnClicked) tradeButtonSize + 10 else tradeButtonSize,
        label = "TradeButtonSizeAnimation"
    )

    LaunchedEffect(isTradeBtnClicked) {
        if (isTradeBtnClicked) {
            delay(200)
            isTradeBtnClicked = false
        }
    }

    Box(modifier = modifier) {
        Column {
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    bottomNavItems.take(bottomNavItems.size / 2).forEach { dest ->
                        NavBarItem(currentDestination, noNavigationBarClick, dest)
                    }

                    Spacer(modifier = Modifier.size(60.dp))

                    bottomNavItems.takeLast(bottomNavItems.size / 2).forEach { dest ->
                        NavBarItem(currentDestination, noNavigationBarClick, dest)
                    }
                }
            }
        }

        Button(
            onClick = {
                showBottomSheet = !showBottomSheet
                isTradeBtnClicked = true
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-25).dp)
                .size(animateSize.dp)
                .rotate(animateRotate),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseSurface)
        ) {
            Icon(
                painter = painterResource(id = if (showBottomSheet) R.drawable.ic_close else R.drawable.ic_trade),
                contentDescription = null,
                modifier = Modifier.rotate(if (showBottomSheet) 0f else -45f),
                tint = MaterialTheme.colorScheme.surface
            )

        }
    }
}

@Composable
private fun RowScope.NavBarItem(
    currentDestination: TopLevelDestination,
    noNavigationBarClick: (TopLevelDestination) -> Unit,
    dest: TopLevelDestination
) {
    val isSelected = currentDestination == dest
    NavigationBarItem(selected = isSelected, onClick = {
        noNavigationBarClick(dest)
    }, icon = {
        Icon(
            painter = painterResource(id = dest.icon),
            contentDescription = dest.name
        )
    },
        label = {
            Text(
                text = stringResource(dest.resourceId),
                style = MaterialTheme.typography.labelSmall
            )
        },
        colors = NavigationBarItemColors(
            selectedIconColor = MaterialTheme.colorScheme.onSurface,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

