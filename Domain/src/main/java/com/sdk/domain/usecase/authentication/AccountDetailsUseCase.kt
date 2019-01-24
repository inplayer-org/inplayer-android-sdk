package com.sdk.domain.usecase.authentication

import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class AccountDetailsUseCase(val appSchedulers: InPlayerSchedulers,
                            val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerDomainUser, Nothing?>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Single<InPlayerDomainUser> {
        return inPlayerAuthenticatorRepository.getUser()
    }
    
}