package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName
import com.sdk.domain.entity.asset.ExternalFeesEntity

data class ExternalFeesApiModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("payment_provider_id") val paymentProviderId: Long?,
    @SerializedName("access_fee_id") val accessFeeId: Long?,
    @SerializedName("external_id") val externalId: String?,
    @SerializedName("merchant_id") val merchantId: Long
) {
    fun mapToEntity() =
        ExternalFeesEntity(
            id = id,
            paymentProviderId = paymentProviderId,
            accessFeeId = accessFeeId,
            externalId = externalId,
            merchantId = merchantId
        )
}
