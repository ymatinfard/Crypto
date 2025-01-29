package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.matin.youtech.annotaions.ComponentRenderer
import com.matin.youtech.crypto.domain.model.Modal
import com.matin.youtech.crypto.sdui.UIComponent
import com.matin.youtech.crypto.sdui.UIRenderer
import com.matin.youtech.crypto.ui.CryptoAppState
import com.matin.youtech.crypto.ui.screen.discover.ActionListener

@ComponentRenderer(dataComponent = Modal::class)
class ModalComponent : UIComponent<Modal> {

    @Composable
    override fun BuildUI(data: Modal, appState: CryptoAppState, action: ActionListener?) {
        CryptoBottomSheet(data, appState, action)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CryptoBottomSheet(modal: Modal, appState: CryptoAppState, action: ActionListener?) {
        val isModalVisible = appState.isModalVisible.collectAsState(false)
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        if (isModalVisible.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    appState.setModalVisibility(false)
                },
                sheetState = sheetState
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    UIRenderer().Render(modal.screen, appState = appState, action = action)
                }
            }
        }
    }
}
    
