@file:Suppress("UNCHECKED_CAST")

package com.matin.youtech.crypto.sdui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matin.youtech.annotaions.Component
import com.matin.youtech.crypto.designsystem.CryptoBottomSheet
import com.matin.youtech.crypto.domain.model.Banner
import com.matin.youtech.crypto.domain.model.Screen


class UIRenderer {
    @Composable
    fun Render(screen: Screen) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            screen.components.forEach { componentData ->
                val rendererClass = componentRenderers[componentData::class.java]
                if (rendererClass != null) {
                    val renderer = rendererClass.getDeclaredConstructor().newInstance()
                    RendererComponent(renderer, componentData)
                } else {
                    DefaultRendererComponent(componentData)
                }
            }
          //  HandleAction(action.value)
        }
    }

    @Composable
    fun HandleAction(action: Action) {
        when (action) {
            is Action.ShowModal -> {
                //  BannerComponent().BuildUI(data = Banner("", listOf(), ""))
                CryptoBottomSheet().BuildUI(
                    data = Screen(
                        "",
                        listOf(
                            Banner(
                                title = "Title",
                                description = listOf("Hey", "Hey"),
                                iconUrl = ""
                            )
                        )
                    )
                )
            }

            is Action.Navigation -> {
                // Show toast
            }

            Action.NoOperation -> {}

        }
    }

    // Todo() it should be injected!
    val actionHandler = RealActionHandler()

    @Composable
    private fun <T : Component> RendererComponent(
        renderer: UIComponent<out T>,
        componentData: Component
    ) {
        @Suppress("UNCHECKED_CAST")
        (renderer as UIComponent<T>).BuildUI(componentData as T, actionHandler)
    }
}