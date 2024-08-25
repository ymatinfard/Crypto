package com.matin.youtech.crypto.data

import com.matin.youtech.crypto.data.model.MarketItemNetwork
import com.matin.youtech.crypto.data.model.ScreenNetwork
import com.matin.youtech.crypto.domain.model.Screen

fun List<MarketItemNetwork>.toDomain() = map { it.toDomain() }

fun ScreenNetwork.toDomain() = Screen(title, components.map { it.toDomain() })