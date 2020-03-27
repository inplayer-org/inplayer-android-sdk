package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

data class AccessFeeModel(
    @SerializedName("id") val id: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("amount") val amount: Float,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("access_type") val accessTypeModel: AccessTypeModel,
    @SerializedName("item_type") val itemType: String,
    @SerializedName("trialPeriodModel") val trialPeriodModel: TrialPeriodModel?,
    @SerializedName("setupFeeModel") val setupFeeModel: SetupFeeModel?,
    @SerializedName("expiresAt")  val expiresAt: Long?
)
