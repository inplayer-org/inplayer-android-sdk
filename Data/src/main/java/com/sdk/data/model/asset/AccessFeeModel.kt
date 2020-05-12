package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

data class AccessFeeModel(
    @SerializedName("id") val id: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("amount") val amount: Float,
    @SerializedName("currency") val currency: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("access_type") val accessTypeModel: AccessTypeModel,
    @SerializedName("item") val accessItemModel: ItemAccessModel,
    @SerializedName("item_type") val itemType: String?,
    @SerializedName("trial_period") val trialPeriodModel: TrialPeriodModel?,
    @SerializedName("setup_fee") val setupFeeModel: SetupFeeModel?,
    @SerializedName("geo_restriction") val geoRestrictionApiModel: GeoRestrictionApiModel? = null,
    @SerializedName("expiresAt") val expiresAt: Long?,
    @SerializedName("created_at") val createdAt: Long?,
    @SerializedName("updated_at") val updatedAt: Long?,
    @SerializedName("starts_at") val startsAt: Long?,
    @SerializedName("external_fees") val externalFees: ExternalFeesApiModel? = null,
    @SerializedName("seasonal_fee") val seasonalFeeApiModel: SeasonalFeeApiModel? = null
)
