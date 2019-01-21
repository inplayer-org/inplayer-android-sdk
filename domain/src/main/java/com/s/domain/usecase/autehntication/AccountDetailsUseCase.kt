package com.s.domain.usecase.autehntication

import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
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