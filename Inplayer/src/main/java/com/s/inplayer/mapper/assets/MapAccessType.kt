package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.AccessTypeEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.InPlayerAccessType

/**
 * Created by victor on 1/6/19
 */
class MapAccessType : DomainMapper<AccessTypeEntity, InPlayerAccessType> {
    
    override fun mapFromDomain(domainEntity: AccessTypeEntity): InPlayerAccessType {
        return InPlayerAccessType(id = domainEntity.id,
                quantity = domainEntity.quantity,
                period = domainEntity.period,
                name = domainEntity.name)
    }
    
    override fun mapToDomain(viewModel: InPlayerAccessType): AccessTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}