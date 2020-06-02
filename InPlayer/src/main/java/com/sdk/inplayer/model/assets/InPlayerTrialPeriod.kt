package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.TrialPeriodEntity


class InPlayerTrialPeriod(
    val quantity: Int,
    val period: String,
    val description: String
) {
    constructor(domainEntity: TrialPeriodEntity) : this(
        quantity = domainEntity.quantity,
        period = domainEntity.period,
        description = domainEntity.description
    )
}
