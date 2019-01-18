package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.InPlayerItemDetails

/**
 * Created by victor on 1/6/19
 */
class MapItemDetails constructor(val mapAccessControlType: MapAccessControlType, val mapItemType: MapItemType)
    : DomainMapper<ItemDetailsEntity, InPlayerItemDetails> {
    
    
    override fun mapFromDomain(domainEntity: ItemDetailsEntity): InPlayerItemDetails {
        return InPlayerItemDetails(id = domainEntity.id, merchantId = domainEntity.merchantId, merchantUUID = domainEntity.merchantUUID, isActive = domainEntity.isActive, title = domainEntity.title,
                inPlayerAccessControlTypeEntity = mapAccessControlType.mapFromDomain(domainEntity.accessControlTypeEntity), inPlayerItemTypeEntity = mapItemType.mapFromDomain(domainEntity.itemTypeEntity), metaData = domainEntity.metaData,
                createdAt = domainEntity.createdAt, updatedAt = domainEntity.updatedAt)
    }
    
    override fun mapToDomain(viewModel: InPlayerItemDetails): ItemDetailsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}
