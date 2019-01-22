package com.sdk.inplayer.mapper.account

import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.account.InPlayerUser

/**
 * Created by victor on 1/4/19
 */
internal class InPlayerUserMapper : DomainMapper<InPlayerDomainUser, InPlayerUser> {
    
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