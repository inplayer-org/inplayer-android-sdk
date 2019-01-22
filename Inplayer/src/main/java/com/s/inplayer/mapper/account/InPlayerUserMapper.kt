package com.s.inplayer.mapper.account

import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.account.InPlayerUser

/**
 * Created by victor on 1/4/19
 */
class InPlayerUserMapper : DomainMapper<InPlayerDomainUser, InPlayerUser> {
    
    override fun mapFromDomain(domainEntity: InPlayerDomainUser): InPlayerUser {
        with(domainEntity) {
            return InPlayerUser(
                    id = id,
                    email = email,
                    fullName = fullName,
                    referrer = referrer,
                    roles = roles,
                    isCompleted = isCompleted,
                    metadata = metadata,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    merchantId = merchantId,
                    merchantUUID = merchantUUID,
                    username = username,
                    uuid = uuid)
        }
    }
    
    override fun mapToDomain(viewModel: InPlayerUser): InPlayerDomainUser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}