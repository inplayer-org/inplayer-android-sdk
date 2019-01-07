package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.ItemDetails

/**
 * Created by victor on 1/6/19
 */
class MapItemDetails constructor(val mapAccessControlType: MapAccessControlType, val mapItemType: MapItemType)
    : DomainMapper<ItemDetailsEntity, ItemDetails> {
    
    
    override fun mapFromDomain(domainEntity: ItemDetailsEntity): ItemDetails {
        return ItemDetails(id = domainEntity.id, merchantId = domainEntity.merchantId, merchantUUID = domainEntity.merchantUUID, isActive = domainEntity.isActive, title = domainEntity.title,
                accessControlTypeEntity = mapAccessControlType.mapFromDomain(domainEntity.accessControlTypeEntity), itemTypeEntity = mapItemType.mapFromDomain(domainEntity.itemTypeEntity), metaData = domainEntity.metaData,
                createdAt = domainEntity.createdAt, updatedAt = domainEntity.updatedAt)
    }
    
    override fun mapToDomain(viewModel: ItemDetails): ItemDetailsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}
