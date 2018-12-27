package com.s.domain.usecase.autehntication

import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.CompletableUseCae
import io.reactivex.Completable

/**
 * Created by victor on 12/26/18
 */
class LogOutUserUseCase constructor(schedulers: MySchedulers,
                                    private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : CompletableUseCae<Nothing?>(schedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Completable {
        return inPlayerAuthenticatorRepository.logout()
    }
    
}
