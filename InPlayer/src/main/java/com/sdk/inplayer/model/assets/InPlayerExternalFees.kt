package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.ExternalFeesEntity

data class InPlayerExternalFees(
    val id: Long?,
    val paymentProviderId: Long?,
    val accessFeeId: Long?,
    val externalId: String?,
    val merchantId: Long
) {
    constructor(externalFeesEntity: ExternalFeesEntity) : this(
        id = externalFeesEntity.id,
        paymentProviderId = externalFeesEntity.paymentProviderId,
        accessFeeId = externalFeesEntity.accessFeeId,
        externalId = externalFeesEntity.externalId,
        merchantId = externalFeesEntity.merchantId
    )
}