package com.matin.youtech.crypto.domain.model

import com.matin.youtech.annotaions.Component
import kotlinx.serialization.Serializable

@Serializable
data class RowTitle(val title: String?, val badge: String? = null): Component
