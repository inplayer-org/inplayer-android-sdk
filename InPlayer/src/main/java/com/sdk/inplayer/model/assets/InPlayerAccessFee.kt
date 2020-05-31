package com.sdk.inplayer.model.assets

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
)
