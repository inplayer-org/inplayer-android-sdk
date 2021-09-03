package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class ForgotPasswordUseCase constructor(appSchedulers: InPlayerSchedulers, private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ForgotPasswordUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            
            return inPlayerAuthenticatorRepository.requestForgotPassword(it.merchantUUID, it.email, it.brandingId)
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
        
    }
    
    data class Params(val merchantUUID: String, val email: String, val brandingId: Int? = null)
}