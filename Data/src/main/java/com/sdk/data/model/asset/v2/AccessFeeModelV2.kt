package com.sdk.data.model.asset.v2

import com.google.gson.annotations.SerializedName
import com.sdk.data.model.asset.*

data class AccessFeeModelV2(
    @SerializedName("id") val id: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("amount") val amount: Float,
    @SerializedName("currency") val currency: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("access_type") val accessTypeModel: AccessTypeModel,
    @SerializedName("item") val accessItemModel: ItemAccessModelV2,
    @SerializedName("item_type") val itemType: String?,
    @SerializedName("trial_period") val trialPeriodModel: TrialPeriodModel?,
    @SerializedName("setup_fee") val setupFeeModel: SetupFeeModel?,
    @SerializedName("geo_restriction") val geoRestrictionApiModel: GeoRestrictionApiModel? = null,
    @SerializedName("expiresAt") val expiresAt: Long?,
    @SerializedName("created_at") val createdAt: Long?,
    @SerializedName("updated_at") val updatedAt: Long?,
    @SerializedName("starts_at") val startsAt: Long?,
    @SerializedName("external_fees") val externalFees: List<ExternalFeesApiModel>? = null,
    @SerializedName("seasonal_fee") val seasonalFeeApiModel: SeasonalFeeApiModel? = null,
    @SerializedName("voucher_rule") val voucherRule: VoucherRuleApiModel? = null
)
