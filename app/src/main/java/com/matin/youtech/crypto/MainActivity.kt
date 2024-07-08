package com.matin.youtech.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matin.youtech.crypto.ui.MainScreen
import com.matin.youtech.crypto.ui.theme.CryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier
                        .padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier) {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun Crypto() {
    CryptoTheme {
        MainContent(Modifier)
    }
}