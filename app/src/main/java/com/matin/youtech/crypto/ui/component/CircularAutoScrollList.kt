package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.matin.youtech.crypto.domain.model.Brand
import com.matin.youtech.crypto.ui.screen.portfolio.brandList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularAutoScrollList(
    modifier: Modifier = Modifier,
    list: List<Brand> = brandList
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var autoScrollEnabled by remember { mutableStateOf(true) }
    val lifecycleOwner = LocalLifecycleOwner.current

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
            delay(500)
            coroutineScope.launch {
                lazyListState.animateScrollBy(40f)
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
                painter = rememberAsyncImagePainter(model = list[boundedIndex].image),
                contentDescription = null
            )
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    autoScrollEnabled = false
                }

                Lifecycle.Event.ON_RESUME -> {
                    autoScrollEnabled = true
                }

                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
