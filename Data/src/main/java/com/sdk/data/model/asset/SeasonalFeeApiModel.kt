package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName
import com.sdk.domain.entity.asset.SeasonalFeeEntity

data class SeasonalFeeApiModel(
    @SerializedName("id") val id: Long,
    @SerializedName("access_fee_id") val accessFeeId: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("current_price_amount") val currentPriceAmount: Double,
    @SerializedName("off_season_access") val offSeasonAccess: Boolean,
    @SerializedName("anchor_date") val anchorDate: Long,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("updated_at") val updatedAt: Long
) {
    fun mapToEntity() =
        SeasonalFeeEntity(
            offSeasonAccess = offSeasonAccess,
            currentPriceAmount = currentPriceAmount,
            anchorDate = anchorDate,
            accessFeeId = accessFeeId,
            merchantId = merchantId,
            createdAt = createdAt,
            id = id,
            updatedAt = updatedAt
        )
}