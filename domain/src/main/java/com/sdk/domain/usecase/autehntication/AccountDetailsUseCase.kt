package com.sdk.domain.usecase.autehntication

import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/26/18
 */
class AccountDetailsUseCase(val appSchedulers: MySchedulers,
                            val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerDomainUser, Nothing?>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Single<InPlayerDomainUser> {
        return inPlayerAuthenticatorRepository.getUser()
    }
    
}