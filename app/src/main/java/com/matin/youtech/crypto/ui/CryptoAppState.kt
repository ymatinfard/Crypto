package com.matin.youtech.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Composable
fun rememberCryptoAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
    ): CryptoAppState {
    return remember(navController) {
        CryptoAppState(navController, coroutineScope)
    }
}

@Stable
class CryptoAppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    val isModalVisible = MutableSharedFlow<Boolean>()

    fun setModalVisibility(isVisible: Boolean) {
        coroutineScope.launch {
            isModalVisible.emit(isVisible)
        }
    }
}