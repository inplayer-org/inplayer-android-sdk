package com.s.inplayer.mapper.assets

import com.s.inplayer.model.assets.InPlayerItemType
import com.s.domain.entity.asset.ItemTypeEntity
import com.s.domain.entity.mapper.DomainMapper

/**
 * Created by victor on 1/6/19
 */
class MapItemType : DomainMapper<ItemTypeEntity, InPlayerItemType> {
    
    override fun mapFromDomain(domainEntity: ItemTypeEntity): InPlayerItemType {
        return InPlayerItemType(id = domainEntity.id, name = domainEntity.name, contentType = domainEntity.contentType,
                host = domainEntity.host, description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: InPlayerItemType): ItemTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}