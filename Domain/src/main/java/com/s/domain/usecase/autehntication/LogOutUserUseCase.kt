package com.s.domain.usecase.autehntication

import com.s.domain.gateway.InPlayerAuthenticator
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.CompletableUseCae
import io.reactivex.Completable

/**
 * Created by victor on 12/26/18
 */
class LogOutUserUseCase constructor(schedulers: MySchedulers,
                                    private val inPlayerAuthenticator: InPlayerAuthenticator)
    : CompletableUseCae<Nothing?>(schedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Completable {
        return inPlayerAuthenticator.logout()
    }
    
}
