package com.s.inplayer.mapper

import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.InPlayerUser

/**
 * Created by victor on 1/4/19
 */
class InPlayerUserMapper : DomainMapper<InPlayerDomainUser, InPlayerUser> {
    
    override fun mapFromDomain(domainEntity: InPlayerDomainUser): InPlayerUser {
        with(domainEntity) {
            return InPlayerUser(id, email, fullName, referrer, roles, isCompleted, createdAt, updatedAt)
        }
    }
    
    
    override fun mapToDomain(viewModel: InPlayerUser): InPlayerDomainUser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}