package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.AccessFeeEntity

class InPlayerAccessFee(
    val id: Long,
    val merchantId: Long,
    val amount: Float,
    val currency: String,
    val description: String,
    val accessType: InPlayerAccessType?,
    val itemType: String,
    val trialPeriod: InPlayerTrialPeriod?,
    val setupFee: InPlayerSetupFee?,
    val expiresAt: Long?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val startsAt: Long?,
    val item: InPlayerItem?,
    val geoRestriction: InPlayerGeoRestriction?,
    val externalFees: List<InPlayerExternalFees>?,
    val seasonalFee: InPlayerSeasonalFee?,
    val voucherRule: InPlayerVoucherRule?
) {
    constructor(domainEntity: AccessFeeEntity) :this(
        id = domainEntity.id,
        merchantId = domainEntity.merchantId,
        amount = domainEntity.amount,
        currency = domainEntity.currency,
        description = domainEntity.description,
        accessType = domainEntity.accessTypeEntity?.let { InPlayerAccessType(it) },
        itemType = domainEntity.itemType,
        trialPeriod = domainEntity.trialPeriodEntity?.let { InPlayerTrialPeriod(it) },
        setupFee = domainEntity.setupFeeEntity?.let { InPlayerSetupFee(it) },
        expiresAt = domainEntity.expiresAt,
        startsAt = domainEntity.startsAt,
        createdAt = domainEntity.createdAt,
        updatedAt = domainEntity.updatedAt,
        geoRestriction = domainEntity.geoRestrictionEntity?.let { InPlayerGeoRestriction(it) },
        seasonalFee = domainEntity.seasonalFeeEntity?.let { InPlayerSeasonalFee(it) },
        externalFees = domainEntity.externalFeesEntity?.map { InPlayerExternalFees(it) },
        item = domainEntity.item?.let { InPlayerItem(it) },
        voucherRule = domainEntity.voucherRule?.let { InPlayerVoucherRule(it) }
    )
}
