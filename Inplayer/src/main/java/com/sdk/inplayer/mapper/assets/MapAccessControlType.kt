package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.AccessControlTypeEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerAccessControlType


internal class MapAccessControlType : DomainMapper<AccessControlTypeEntity, InPlayerAccessControlType> {
    
    override fun mapFromDomain(domainEntity: AccessControlTypeEntity): InPlayerAccessControlType {
        return InPlayerAccessControlType(id = domainEntity.id, name = domainEntity.name, auth = domainEntity.auth)
    }
    
    override fun mapToDomain(viewModel: InPlayerAccessControlType): AccessControlTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}