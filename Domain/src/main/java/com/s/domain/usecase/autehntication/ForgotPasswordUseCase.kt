package com.s.domain.usecase.autehntication

import com.s.domain.exception.InPlayerInvalidFieldsException
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.isValidEmail
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/26/18
 */
class ForgotPasswordUseCase constructor(appSchedulers: MySchedulers, val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ForgotPasswordUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            
            if (!it.email.isValidEmail())
                return Single.error(InPlayerInvalidFieldsException("Email is not in valid format"))
            
            return inPlayerAuthenticatorRepository.requestForgotPassword(it.merchantUUID, it.email)
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
        
    }
    
    data class Params(val merchantUUID: String, val email: String)
}