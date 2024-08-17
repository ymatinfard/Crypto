package com.matin.youtech.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matin.youtech.crypto.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                    MainContent()
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CryptoApp(modifier)
    }
}