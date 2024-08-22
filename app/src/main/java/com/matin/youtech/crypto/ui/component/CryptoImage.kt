package com.matin.youtech.crypto.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun CryptoImageLoader(modifier: Modifier = Modifier, url: String) {
    Image(
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = "Image",
        modifier = modifier
    )
}