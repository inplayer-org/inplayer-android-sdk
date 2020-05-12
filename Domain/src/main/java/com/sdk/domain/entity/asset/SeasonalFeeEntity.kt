package com.sdk.domain.entity.asset

data class SeasonalFeeEntity(
    val offSeasonAccess: Boolean,
    val currentPriceAmount: Double,
    val anchorDate: Long
)
