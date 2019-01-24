package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class SetNewPasswordUseCase(appSchedulers: InPlayerSchedulers,
                            private val inPlayerAccountRepository: InPlayerAccountRepository) : SingleUseCase<String, SetNewPasswordUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            return inPlayerAccountRepository.setNewPassword(it.token, it.password, it.passwordConfirmation)
        }
        
        throw IllegalStateException("Params Can't be null for SetNewPasswordUseCase")
    }
    
    data class Params(val token: String, val password: String, val passwordConfirmation: String)
}