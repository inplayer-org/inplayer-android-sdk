package com.sdk.inplayer.mapper.account

import com.sdk.domain.entity.account.AuthorizationHolder
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.account.InPlayerAuthorizationModel

/**
 * Created by victor on 1/21/19
 */
class AuthorizationModelMapper constructor(private val inPlayerUserMapper: InPlayerUserMapper)
    : DomainMapper<AuthorizationHolder,InPlayerAuthorizationModel> {
    
    override fun mapFromDomain(domainEntity: AuthorizationHolder): InPlayerAuthorizationModel {
        return InPlayerAuthorizationModel(
                accessToken = domainEntity.accessToken,
                refreshToken =  domainEntity.refreshToken,
                account = inPlayerUserMapper.mapFromDomain(domainEntity.account))
        
    }
    
    override fun mapToDomain(viewModel: InPlayerAuthorizationModel): AuthorizationHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}