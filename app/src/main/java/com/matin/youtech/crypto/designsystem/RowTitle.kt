package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matin.youtech.crypto.domain.model.RowTitle

@Composable
fun RowTitle(rowTitle: RowTitle) {
    Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = SCREEN_SIDE_PADDING.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = rowTitle.title ?: "", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
        if (rowTitle.badge != null) {
            Box(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
            {
                Text(rowTitle.badge, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}