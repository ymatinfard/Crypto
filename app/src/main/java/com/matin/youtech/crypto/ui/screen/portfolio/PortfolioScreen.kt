package com.matin.youtech.crypto.ui.screen.portfolio

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.data.Data
import com.matin.youtech.crypto.domain.PortfolioItem

@Composable
fun PortfolioScreenRoute(viewModel: PortfolioScreenViewModel) {
    val uiState = viewModel.getPortfolio()
        .collectAsStateWithLifecycle(Data())
    PortfolioScreen(uiState)
}

@Composable
fun PortfolioScreen(uiState: State<Data<List<PortfolioItem>>>) {
    Log.d("PortfolioScreen", "PortfolioScreen: $uiState")
    when {
        uiState.value.content != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(uiState.value.content!!) {
                        Text(text = it.coinName)
                    }
                }
            }

        }

        uiState.value.loading -> {
            Text(text = "Please wait...")
        }

        uiState.value.error != null -> {
            Text(text = "Error")
        }
    }
}