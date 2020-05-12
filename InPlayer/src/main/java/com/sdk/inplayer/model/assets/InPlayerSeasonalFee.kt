package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.SeasonalFeeEntity

data class InPlayerSeasonalFee(
    val offSeasonAccess: Boolean,
    val currentPriceAmount: Double,
    val anchorDate: Long
) {
    constructor(seasonalFeeModel: SeasonalFeeEntity) : this(
        offSeasonAccess = seasonalFeeModel.offSeasonAccess,
        currentPriceAmount = seasonalFeeModel.currentPriceAmount,
        anchorDate = seasonalFeeModel.anchorDate
    )
}
