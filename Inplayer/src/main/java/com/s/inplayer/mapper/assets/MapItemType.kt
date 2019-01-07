package com.s.inplayer.mapper.assets

import com.s.inplayer.model.assets.ItemType
import com.s.domain.entity.asset.ItemTypeEntity
import com.s.domain.entity.mapper.DomainMapper

/**
 * Created by victor on 1/6/19
 */
class MapItemType : DomainMapper<ItemTypeEntity, ItemType> {
    
    override fun mapFromDomain(domainEntity: ItemTypeEntity): ItemType {
        return ItemType(id = domainEntity.id, name = domainEntity.name, contentType = domainEntity.contentType,
                host = domainEntity.host, description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: ItemType): ItemTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}