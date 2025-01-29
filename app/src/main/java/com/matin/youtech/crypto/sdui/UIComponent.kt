package com.matin.youtech.crypto.sdui

import androidx.compose.runtime.Composable
import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.discover.ActionListener

interface UIComponent<T: Component> {
    @Composable
    fun BuildUI(data: T, appState: CryptoAppState, action: ActionListener?)
}