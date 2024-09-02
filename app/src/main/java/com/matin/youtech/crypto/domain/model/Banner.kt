package com.matin.youtech.crypto.domain.model

import com.matin.youtech.annotaions.Component


data class Banner(val title: String, val description: List<String>, val iconUrl: String): Component
