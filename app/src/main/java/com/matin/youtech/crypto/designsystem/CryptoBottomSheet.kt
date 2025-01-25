package com.matin.youtech.crypto.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.domain.model.Screen
import com.matin.youtech.crypto.sdui.ActionHandler
import com.matin.youtech.crypto.sdui.UIComponent
import com.matin.youtech.crypto.sdui.UIRenderer

class CryptoBottomSheet : UIComponent<Screen> {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun BuildUI(data: Screen, actionHandler: ActionHandler) {
       // DataBottomSheet()
        ModalBottomSheet(onDismissRequest = {}) {
            UIRenderer().Render(screen = data)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DataBottomSheet() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "This is the bottom sheet content")
            }
        },
        sheetPeekHeight = 56.dp // Height of the sheet when collapsed
    ) {
        // Main content of the scaffold
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
//                coroutineScope.launch {
//                    if (scaffoldState.bottomSheetState.isCollapsed) {
//                        scaffoldState.bottomSheetState.expand()
//                    } else {
//                        scaffoldState.bottomSheetState.collapse()
//                    }
//                }
            }) {
                Text("Toggle Bottom Sheet")
            }
        }
    }
}

@Composable
fun BottomSheetContent() {
    //  UIRenderer().Render(screen = screen)
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hey")
        Button(onClick = { }) {
            Text(text = "Show Bottom Sheet")
        }
    }
}
    
