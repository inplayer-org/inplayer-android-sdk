package com.sdk.domain.entity.asset

data class SeasonalFeeEntity(
    val id: Long,
    val accessFeeId: Long,
    val merchantId: Long,
    val currentPriceAmount: Double,
    val offSeasonAccess: Boolean,
    val anchorDate: Long,
    val createdAt: Long,
    val updatedAt: Long
)
