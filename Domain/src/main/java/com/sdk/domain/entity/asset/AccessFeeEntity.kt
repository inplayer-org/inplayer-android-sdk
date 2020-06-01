package com.sdk.domain.entity.asset


data class AccessFeeEntity(
    val id: Long,
    val merchantId: Long,
    val amount: Float,
    val currency: String,
    val description: String,
    val accessTypeEntity: AccessTypeEntity?,
    val item: ItemDetailsEntity?,
    val itemType: String,
    val trialPeriodEntity: TrialPeriodEntity?,
    val setupFeeEntity: SetupFeeEntity?,
    val geoRestrictionEntity: GeoRestrictionEntity?,
    val externalFeesEntity: List<ExternalFeesEntity>?,
    val seasonalFeeEntity: SeasonalFeeEntity?,
    val expiresAt: Long?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val startsAt: Long?,
    val voucherRule: VoucherRuleEntity?
)