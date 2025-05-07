@file:Suppress("UNCHECKED_CAST")

package com.matin.youtech.crypto.sdui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.discover.ActionListener


class UIRenderer {
    @Composable
    fun Render(screen: Screen, appState: CryptoAppState, action: ActionListener?) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            screen.components.forEach { componentData ->
                val rendererClass = componentRenderers[componentData::class.java]
                if (rendererClass != null) {
                    val renderer = rendererClass.getDeclaredConstructor().newInstance()
                    RendererComponent(renderer, componentData, appState, action)
                } else {
                    DefaultRendererComponent(componentData)
                }
            }
        }
    }

    @Composable
    private fun <T : Component> RendererComponent(
        renderer: UIComponent<out T>,
        componentData: Component,
        appState: CryptoAppState,
        action: ActionListener? = null,
    ) {
        @Suppress("UNCHECKED_CAST")
        (renderer as UIComponent<T>).BuildUI(componentData as T, appState, action)
    }
}