package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.ItemTypeEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerItemType

/**
 * Created by victor on 1/6/19
 */
internal class MapItemType : DomainMapper<ItemTypeEntity, InPlayerItemType> {
    
    override fun mapFromDomain(domainEntity: ItemTypeEntity): InPlayerItemType {
        return InPlayerItemType(id = domainEntity.id,
                name = domainEntity.name,
                contentType = domainEntity.contentType,
                host = domainEntity.host,
                description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: InPlayerItemType): ItemTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}