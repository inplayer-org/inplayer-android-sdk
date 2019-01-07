package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.ItemAccessEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.ItemAccess

/**
 * Created by victor on 1/6/19
 */
class MapItemAccess constructor(private val mapItemDetails: MapItemDetails)
    : DomainMapper<ItemAccessEntity, ItemAccess> {
    
    
    override fun mapFromDomain(domainEntity: ItemAccessEntity): ItemAccess {
        return ItemAccess(id = domainEntity.id, accountId = domainEntity.accountId, customerId = domainEntity.customerId, customerUUID = domainEntity.customerUUID,
                ipAddress = domainEntity.ipAddress, countryCode = domainEntity.countryCode, createdAt = domainEntity.createdAt, expiresAt = domainEntity.expiresAt,
                itemDetailsEntity = mapItemDetails.mapFromDomain(domainEntity.itemDetailsEntity))
    }
    
    override fun mapToDomain(viewModel: ItemAccess): ItemAccessEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}