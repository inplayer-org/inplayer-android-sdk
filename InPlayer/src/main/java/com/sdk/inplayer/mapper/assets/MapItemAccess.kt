package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.AccessControlTypeEntity
import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerAccessControlType
import com.sdk.inplayer.model.assets.InPlayerItemAccess


internal class MapItemAccess constructor(private val mapItemDetails: MapItemDetails)
    : DomainMapper<ItemAccessEntity, InPlayerItemAccess> {
    
    
    override fun mapFromDomain(domainEntity: ItemAccessEntity): InPlayerItemAccess {
        return InPlayerItemAccess(id = domainEntity.id,
                accountId = domainEntity.accountId,
                customerId = domainEntity.customerId,
                customerUUID = domainEntity.customerUUID,
                ipAddress = domainEntity.ipAddress,
                countryCode = domainEntity.countryCode,
                createdAt = domainEntity.createdAt,
                expiresAt = domainEntity.expiresAt,
                itemEntity = domainEntity.itemDetailsEntity?.let { mapItemDetails.mapFromDomain(it) },
                active = domainEntity.active,
                merchantId = domainEntity.merchantId,
                merchantUUID = domainEntity.merchantUUID,
                accessControlType = domainEntity.accessControlTypeEntity?.let { mapAccessControlType(it) },
                content = domainEntity.content ?: "",
                title = domainEntity.title ?: ""
            )
    }
    
    override fun mapToDomain(viewModel: InPlayerItemAccess): ItemAccessEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    private fun mapAccessControlType(accessControlTypeEntity: AccessControlTypeEntity): InPlayerAccessControlType {
        return InPlayerAccessControlType(
            accessControlTypeEntity.id,
            accessControlTypeEntity.name,
            accessControlTypeEntity.auth
        )
    }
}