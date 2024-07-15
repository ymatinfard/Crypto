package com.matin.youtech.crypto.ui.screen

import androidx.lifecycle.ViewModel


class MainScreenViewModel : ViewModel() {

    fun intentToAction(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.DepositClick -> {
                // TODO
                println("Deposit clicked")
            }

            is MainScreenIntent.MarketTabClick -> {
                //Todo
                println("MarketTab clicked $$intent")
            }
        }
    }

}