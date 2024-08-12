package com.matin.youtech.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matin.youtech.crypto.ui.screen.main.MainScreenRoute
import com.matin.youtech.crypto.ui.screen.main.MainScreenViewModel
import com.matin.youtech.crypto.ui.screen.portfolio.PortfolioScreenRoute
import com.matin.youtech.crypto.ui.screen.portfolio.PortfolioScreenViewModel
import com.matin.youtech.crypto.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val portfolioViewModel: PortfolioScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
     //   val viewModel: MainScreenViewModel by viewModels()

        setContent {

            CryptoTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PortfolioScreenRoute(modifier = Modifier.padding(innerPadding), viewModel = portfolioViewModel)// viewModel = portfolioViewModel)
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, viewModel: MainScreenViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        MainScreenRoute(viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun Crypto() {
    CryptoTheme {
        // MainContent()
    }
}