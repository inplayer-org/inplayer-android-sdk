package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

data class SeasonalFeeApiModel(
    @SerializedName("off_season_access") val offSeasonAccess: Boolean,
    @SerializedName("current_price_amount") val currentPriceAmount: Double,
    @SerializedName("anchor_date") val anchorDate: Long
)