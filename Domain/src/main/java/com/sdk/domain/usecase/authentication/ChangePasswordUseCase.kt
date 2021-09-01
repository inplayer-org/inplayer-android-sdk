package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class ChangePasswordUseCase constructor(private val inPlayerSchedulers: InPlayerSchedulers,
                                        private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ChangePasswordUseCase.Params>(inPlayerSchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        params?.let {
            
            return inPlayerAuthenticatorRepository.changePassword(it.newPassword, it.newPasswordConfirmation, it.oldPassword, it.brandingId)
            
        }
        
        throw IllegalStateException("Params Can't be null for ChangePasswordUseCase")
    }
    
    data class Params(val newPassword: String, val newPasswordConfirmation: String, val oldPassword: String, val brandingId: String? = null)
}