package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.domain.model.LineSpace

@Composable
fun LineSpace(lineSpace: LineSpace) {
    Spacer(modifier = Modifier.fillMaxWidth().height((lineSpace.lineCount * 5).dp))
}