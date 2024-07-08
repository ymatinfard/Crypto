package com.matin.youtech.crypto.ui.component

import HorizontalPagerIndicator
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerPager() {
    val state = rememberPagerState(pageCount = { BANNER_PAGE_COUNT })
    val bannerText = arrayOf("Unlock 100 USDT welcome rewards", "Invite your friend to get 40$")

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .width(2.dp), color = MaterialTheme.colorScheme.onSurface
        )

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            state = state
        ) { page ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = buildAnnotatedString {
                    pushStyle(SpanStyle(color = MaterialTheme.colorScheme.tertiary))
                    append("New User Zone")
                    pop()
                    appendLine()
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append(bannerText[page])
                } )
                Image(
                    modifier = Modifier.size(42.dp),
                    painter = painterResource(id = R.drawable.ic_bitcoin_increase),
                    contentDescription = "bitcoin banner"
                )
            }
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            pageCount = 2,
            currentPage = state.currentPage,
            targetPage = state.targetPage,
            currentPageOffsetFraction = 0.0f
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .width(2.dp), color = MaterialTheme.colorScheme.onSurface
        )
    }
}

const val BANNER_PAGE_COUNT = 2