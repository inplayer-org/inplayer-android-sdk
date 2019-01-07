package com.s.inplayer.mapper.assets

import com.s.inplayer.model.assets.AccessType
import com.s.domain.entity.asset.AccessTypeEntity
import com.s.domain.entity.mapper.DomainMapper

/**
 * Created by victor on 1/6/19
 */
class MapAccessType : DomainMapper<AccessTypeEntity, AccessType> {
    
    override fun mapFromDomain(domainEntity: AccessTypeEntity): AccessType {
        return AccessType(id = domainEntity.id, quantity = domainEntity.quantity,
                period = domainEntity.period, name = domainEntity.name)
    }
    
    override fun mapToDomain(viewModel: AccessType): AccessTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}