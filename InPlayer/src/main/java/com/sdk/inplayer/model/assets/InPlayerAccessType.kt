package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.AccessTypeEntity


class InPlayerAccessType(
    val id: Long,
    val name: String,
    val quantity: Int,
    val period: String
) {
    constructor(domainEntity: AccessTypeEntity) : this(
        id = domainEntity.id,
        quantity = domainEntity.quantity,
        period = domainEntity.period,
        name = domainEntity.name
    )
}