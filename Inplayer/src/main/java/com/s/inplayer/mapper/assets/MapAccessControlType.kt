package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.AccessControlTypeEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.AccessControlType

/**
 * Created by victor on 1/6/19
 */
class MapAccessControlType : DomainMapper<AccessControlTypeEntity, AccessControlType> {
    
    override fun mapFromDomain(domainEntity: AccessControlTypeEntity): AccessControlType {
        return AccessControlType(id = domainEntity.id, name = domainEntity.name, auth = domainEntity.auth)
    }
    
    override fun mapToDomain(viewModel: AccessControlType): AccessControlTypeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}