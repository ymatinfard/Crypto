package com.matin.youtech.crypto.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RowTitle(val title: String?, val badge: String? = null): Component
