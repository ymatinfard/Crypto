package com.matin.youtech.crypto.data.model

import com.matin.youtech.annotaions.Component

interface ComponentNetwork {
    val componentType: ComponentType
    fun toDomain(): Component
}