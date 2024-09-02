package com.matin.youtech.crypto.sdui

import androidx.compose.runtime.Composable
import com.matin.youtech.annotaions.Component

interface UIComponent<T: Component> {
    @Composable
    fun BuildUI(data: T)
}