package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.SeasonalFeeEntity

data class InPlayerSeasonalFee(
    val id: Long,
    val accessFeeId: Long,
    val merchantId: Long,
    val currentPriceAmount: Double,
    val offSeasonAccess: Boolean,
    val anchorDate: Long,
    val createdAt: Long,
    val updatedAt: Long
) {
    constructor(seasonalFeeModel: SeasonalFeeEntity) : this(
        offSeasonAccess = seasonalFeeModel.offSeasonAccess,
        currentPriceAmount = seasonalFeeModel.currentPriceAmount,
        anchorDate = seasonalFeeModel.anchorDate,
        accessFeeId = seasonalFeeModel.accessFeeId,
        merchantId = seasonalFeeModel.merchantId,
        createdAt = seasonalFeeModel.createdAt,
        id = seasonalFeeModel.id,
        updatedAt = seasonalFeeModel.updatedAt
    )
}
