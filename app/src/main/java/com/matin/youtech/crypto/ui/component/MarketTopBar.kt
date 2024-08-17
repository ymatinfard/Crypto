package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MarketTopBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.AddCircle,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "chain"
            )
        }

        Row {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "notification"
                )
            }
        }
    }
}