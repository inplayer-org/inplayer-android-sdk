package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.AccessControlTypeEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.InPlayerAccessControlType

/**
 * Created by victor on 1/6/19
 */
class MapAccessControlType : DomainMapper<AccessControlTypeEntity, InPlayerAccessControlType> {
    
    override fun mapFromDomain(domainEntity: AccessControlTypeEntity): InPlayerAccessControlType {
        return InPlayerAccessControlType(id = domainEntity.id, name = domainEntity.name, auth = domainEntity.auth)
    }
    
    override fun mapToDomain(viewModel: InPlayerAccessControlType): AccessControlTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}