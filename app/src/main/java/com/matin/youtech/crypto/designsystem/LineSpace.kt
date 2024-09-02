package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.youtech.annotaions.ComponentRenderer
import com.matin.youtech.crypto.domain.model.LineSpace
import com.matin.youtech.crypto.sdui.UIComponent

@ComponentRenderer(LineSpace::class)
class LineSpaceComponent: UIComponent<LineSpace> {

    @Composable
    override fun BuildUI(data: LineSpace) {
        Spacer(modifier = Modifier.fillMaxWidth().height((data.lineCount * 5).dp))
    }
}