package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerItemAccess

/**
 * Created by victor on 1/6/19
 */
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
                itemEntity = mapItemDetails.mapFromDomain(domainEntity.itemDetailsEntity))
    }
    
    override fun mapToDomain(viewModel: InPlayerItemAccess): ItemAccessEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}