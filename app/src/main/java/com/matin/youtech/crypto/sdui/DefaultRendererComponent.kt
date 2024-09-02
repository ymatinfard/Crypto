package com.matin.youtech.crypto.sdui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.matin.youtech.annotaions.Component

@Composable
fun DefaultRendererComponent(componentData: Component) {
    Text(text = "No renderer available for ${componentData::class.java.simpleName}")
}
