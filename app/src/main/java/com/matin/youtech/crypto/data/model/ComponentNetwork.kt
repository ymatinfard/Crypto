package com.matin.youtech.crypto.data.model

import com.matin.youtech.crypto.domain.model.Component

interface ComponentNetwork {
    val componentType: ComponentType
    fun toDomain(): Component
}