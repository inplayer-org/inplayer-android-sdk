package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.AccessControlTypeEntity

class InPlayerAccessControlType(
    val id: Long,
    val name: String,
    val auth: Boolean
) {
    constructor(domainEntity: AccessControlTypeEntity): this(
        id = domainEntity.id, name = domainEntity.name, auth = domainEntity.auth
    )
}
