package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.CompletableUseCae
import io.reactivex.Completable


class LogOutUserUseCase constructor(schedulers: InPlayerSchedulers,
                                    private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : CompletableUseCae<Nothing?>(schedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Completable {
        return inPlayerAuthenticatorRepository.logout()
    }
    
}
