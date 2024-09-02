@file:Suppress("UNCHECKED_CAST")

package com.matin.youtech.crypto.sdui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matin.youtech.annotaions.Component

import com.matin.youtech.crypto.domain.model.Screen


object UIRenderer {
    @Composable
    fun Render(screen: Screen) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(screen.components) { componentData ->
                val rendererClass = componentRenderers[componentData::class.java]
                if (rendererClass != null) {
                    val renderer = rendererClass.getDeclaredConstructor().newInstance()
                    RendererComponent(renderer, componentData)
                } else {
                    DefaultRendererComponent(componentData)
                }
            }
        }
    }

    @Composable
    private fun <T : Component> RendererComponent(
        renderer: UIComponent<out T>,
        componentData: Component
    ) {
        @Suppress("UNCHECKED_CAST")
        (renderer as UIComponent<T>).BuildUI(componentData as T)
    }
}