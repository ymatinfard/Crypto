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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.matin.youtech.crypto.R
import com.matin.youtech.crypto.ui.theme.CryptoTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerPager() {
    val state = rememberPagerState(pageCount = { BANNER_PAGE_COUNT })
    val bannerText = arrayOf(
        stringResource(R.string.invitation_reward_msg),
        stringResource(R.string.invitation_reward_msg)
    )

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .width(2.dp), color = MaterialTheme.colorScheme.surfaceVariant
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
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(color = MaterialTheme.colorScheme.tertiary))
                        append(stringResource(R.string.new_user_zone))
                        pop()
                        appendLine()
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append(bannerText[page])
                    })
                Image(
                    modifier = Modifier.size(42.dp),
                    painter = painterResource(id = R.drawable.ic_bitcoin_increase),
                    contentDescription = stringResource(R.string.invite_your_friend_to_get_40)
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
                .width(2.dp), color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

const val BANNER_PAGE_COUNT = 2

@ThemePreviews
@Composable
fun BannerPagerPreview() {
    CryptoTheme {
        BannerPager()
    }
}