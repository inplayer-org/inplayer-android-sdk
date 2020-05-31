package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.ItemAccessEntity

class InPlayerItemAccess(
    val id: Long,
    val merchantId: Long,
    val merchantUUID: String,
    val accountId: Long,
    val customerId: Long,
    val customerUUID: String,
    val ipAddress: String,
    val countryCode: String,
    val createdAt: Long,
    val expiresAt: Long,
    val itemEntity: InPlayerItem?,
    val accessControlType: InPlayerAccessControlType?,
    val active: Boolean,
    val content: String,
    val title: String
) {
    constructor(domainEntity: ItemAccessEntity) : this(
        id = domainEntity.id,
        accountId = domainEntity.accountId,
        customerId = domainEntity.customerId,
        customerUUID = domainEntity.customerUUID,
        ipAddress = domainEntity.ipAddress,
        countryCode = domainEntity.countryCode,
        createdAt = domainEntity.createdAt,
        expiresAt = domainEntity.expiresAt,
        itemEntity = domainEntity.itemDetailsEntity?.let { InPlayerItem(it) },
        active = domainEntity.active,
        merchantId = domainEntity.merchantId,
        merchantUUID = domainEntity.merchantUUID,
        accessControlType = domainEntity.accessControlTypeEntity?.let { InPlayerAccessControlType(it) },
        content = domainEntity.content ?: "",
        title = domainEntity.title ?: ""
    )
}
