package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

data class ExternalFeesApiModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("payment_provider_id") val paymentProviderId: Long?,
    @SerializedName("access_fee_id") val accessFeeId: Long?,
    @SerializedName("external_id") val externalId: String?,
    @SerializedName("merchant_id") val merchantId: Long
)
