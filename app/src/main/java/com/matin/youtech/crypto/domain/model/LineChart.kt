package com.matin.youtech.crypto.domain.model

import com.matin.youtech.annotaions.Component

data class LineChart(val label: String, val points: List<Double>): Component
