package com.matin.youtech.crypto

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.matin.youtech.crypto.designsystem.CryptoBottomNavigation
import com.matin.youtech.crypto.navigation.CryptoNavHost
import com.matin.youtech.crypto.navigation.TopLevelDestination

@Composable
fun CryptoApp(modifier: Modifier) {
    val navController = rememberNavController()
    var selectedDestination by remember {
        mutableStateOf(TopLevelDestination.Market)
    }

    Column {
        CryptoNavHost(modifier = Modifier.weight(1f), navController = navController)
        CryptoBottomNavigation(
            currentDestination = selectedDestination,
            noNavigationBarClick = { selectedDest ->
                selectedDestination = selectedDest
                navController.navigate(selectedDest.route)
            },
            bottomNavItems = TopLevelDestination.entries
        )
    }
}
