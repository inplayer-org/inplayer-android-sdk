package com.s.domain.usecase.autehntication

import com.s.domain.entity.InPlayerUser
import com.s.domain.gateway.InPlayerAuthenticator
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/26/18
 */
class AccountDetailsUseCase(val appSchedulers: MySchedulers,
                            val inPlayerAuthenticator: InPlayerAuthenticator)
    : SingleUseCase<InPlayerUser, Nothing?>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Single<InPlayerUser> {
        return inPlayerAuthenticator.getUser()
    }
    
}