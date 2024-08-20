package com.matin.youtech.crypto.ui.screen.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.data.Data
import com.matin.youtech.crypto.designsystem.ClickableTabs
import com.matin.youtech.crypto.domain.model.Brand
import com.matin.youtech.crypto.domain.model.Portfolio
import com.matin.youtech.crypto.ui.component.CryptoLoadingWheel
import com.matin.youtech.crypto.ui.component.MarketItemRow
import com.matin.youtech.crypto.ui.theme.transparentBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PortfolioScreenRoute(viewModel: PortfolioScreenViewModel) {
    val uiState = viewModel.getPortfolio().collectAsStateWithLifecycle(Data())
    PortfolioScreen(uiState)
}

@Composable
fun PortfolioScreen(uiState: State<Data<Portfolio>>) {
    when {
        uiState.value.content != null -> {
            PortfolioScreenContent(uiState.value.content!!)
        }

        uiState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CryptoLoadingWheel(
                    modifier = Modifier.align(Alignment.Center),
                    contentDesc = stringResource(R.string.loading)
                )
            }
        }

        uiState.value.error != null -> {
            Text(text = "Error")
        }
    }
}

@Composable
private fun PortfolioScreenContent(data: Portfolio) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val topSectionHeight = maxHeight * .6f
        val selectedTabIndex = remember {
            mutableIntStateOf(0)
        }
        val isSystemInDarkTheme = isSystemInDarkTheme()

        LazyColumn {
            item {
                Box {
                    Image(
                        modifier = Modifier
                            .height(topSectionHeight)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.portfolio_bg),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                    if (isSystemInDarkTheme) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(topSectionHeight)
                                .background(Color.Black.copy(alpha = 0.5f)) // 50% opaque black overlay
                        )
                    }
                    TopContent(modifier = Modifier.align(Alignment.Center))
                    CircularAutoScrollList(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 30.dp)
                    )
                }

                ClickableTabs(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(40.dp)
                        .offset(y = (-20).dp),
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

@Preview(showBackground = true)
@Composable
fun TopContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Start investing",
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Everything you need to start investing, all in one place",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = transparentBackground)
        ) {
            Text(text = "Get started")
        }
    }
}

@Preview
@Composable
fun CircularAutoScrollList(modifier: Modifier = Modifier, list: List<Brand> = brandList) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var autoScrollEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Virtual index to scroll to left and right infinitive
        val middle = Int.MAX_VALUE / 2
        lazyListState.scrollToItem(middle)
    }

    // Detect when the user is scrolling manually
    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            autoScrollEnabled = false
        } else {
            delay(1000) // Allow some idle time before re-enabling auto-scroll
            autoScrollEnabled = true
        }
    }

    LaunchedEffect(autoScrollEnabled) {
        while (autoScrollEnabled) {
            if (!lazyListState.isScrollInProgress) {
                delay(500)
                coroutineScope.launch {
                    lazyListState.animateScrollBy(40f)
                }
            }
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        items(Int.MAX_VALUE) { index ->
            // To enable circular scrolling
            val boundedIndex = index % brandList.size
            Image(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .border(
                        width = 2.dp, color = MaterialTheme.colorScheme.outlineVariant, CircleShape
                    ),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = list[boundedIndex].image),
                contentDescription = null
            )
        }
    }
}

val PORTFOLIO_TAB_ITEMS = listOf("Portfolio", "Discover")
val PORTFOLIO_TAB_ITEM_INDEX = 0
val brandList = listOf(
    Brand("Brand 1", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
    Brand("Brand 2", R.drawable.ic_launcher_foreground),
)
