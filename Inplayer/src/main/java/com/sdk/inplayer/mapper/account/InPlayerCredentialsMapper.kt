package com.sdk.inplayer.mapper.account

import com.sdk.domain.entity.account.CredentialsEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.account.InPlayerCredentials

/**
 * Created by victor on 1/21/19
 */
internal class InPlayerCredentialsMapper : DomainMapper<CredentialsEntity, InPlayerCredentials> {
    
    override fun mapFromDomain(domainEntity: CredentialsEntity): InPlayerCredentials {
        return InPlayerCredentials(accessToken = domainEntity.accessToken, refreshToken = domainEntity.refreshToken)
    }
    
    override fun mapToDomain(viewModel: InPlayerCredentials): CredentialsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}