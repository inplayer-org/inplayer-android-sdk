package com.sdk.domain.usecase.autehntication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/26/18
 */
class ForgotPasswordUseCase constructor(appSchedulers: MySchedulers, val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ForgotPasswordUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            
            return inPlayerAuthenticatorRepository.requestForgotPassword(it.merchantUUID, it.email)
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
        
    }
    
    data class Params(val merchantUUID: String, val email: String)
}