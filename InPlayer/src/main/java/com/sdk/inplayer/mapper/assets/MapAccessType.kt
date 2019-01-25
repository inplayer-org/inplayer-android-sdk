package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.AccessTypeEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerAccessType


internal class MapAccessType : DomainMapper<AccessTypeEntity, InPlayerAccessType> {
    
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