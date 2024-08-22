package com.matin.youtech.crypto.ui.screen.portfolio

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.data.Data
import com.matin.youtech.crypto.designsystem.ClickableTabs
import com.matin.youtech.crypto.domain.Portfolio
import com.matin.youtech.crypto.ui.component.MarketItemRow

@Composable
fun PortfolioScreenRoute(viewModel: PortfolioScreenViewModel) {
    val uiState = viewModel.getPortfolio()
        .collectAsStateWithLifecycle(Data())
    PortfolioScreen(uiState)
}

@Composable
fun PortfolioScreen(uiState: State<Data<Portfolio>>) {
    Log.d("PortfolioScreen", "PortfolioScreen: $uiState")
    when {
        uiState.value.content != null -> {
            PortfolioScreenContent(uiState.value.content!!)
        }

        uiState.value.loading -> {
            Text(text = "Please wait...")
        }

        uiState.value.error != null -> {
            Text(text = "Error")
        }
    }
}

@Composable
private fun PortfolioScreenContent(data: Portfolio) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val height = maxHeight
        val selectedTabIndex = remember {
            mutableIntStateOf(0)
        }
        val isSystemInDarkTheme = isSystemInDarkTheme()

        LazyColumn {
            item {
                Box {
                    Image(
                        modifier = Modifier
                            .height(height / 2)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.portfolio_bg),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                    if (isSystemInDarkTheme) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height / 2)
                                .background(Color.Black.copy(alpha = 0.5f)) // 50% opaque black overlay
                        )
                    }
                }

                ClickableTabs(
                    modifier = Modifier.padding(horizontal = 16.dp).height(40.dp).offset(y = (-20).dp),
                    selectedItem = selectedTabIndex.intValue,
                    tabsList = PORTFOLIO_TAB_ITEMS
                ) {
                    selectedTabIndex.intValue = it
                }
            }

            if (selectedTabIndex.intValue == PORTFOLIO_TAB_ITEM_INDEX) {
                items(data.marketItems) {
                    MarketItemRow(modifier = Modifier.padding(horizontal = 20.dp), it)
                }
            }
        }
    }
}

val PORTFOLIO_TAB_ITEMS = listOf("Portfolio", "Discover")
val PORTFOLIO_TAB_ITEM_INDEX = 0