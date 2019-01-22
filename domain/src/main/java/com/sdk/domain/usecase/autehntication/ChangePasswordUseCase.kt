package com.sdk.domain.usecase.autehntication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/26/18
 */
class ChangePasswordUseCase constructor(val mySchedulers: MySchedulers,
                                        val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ChangePasswordUseCase.Params>(mySchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        params?.let {
            
            return inPlayerAuthenticatorRepository.changePassword(it.newPassword, it.newPasswordConfirmation, it.oldPassword)
            
        }
        
        throw IllegalStateException("Params Can't be null for ChangePasswordUseCase")
    }
    
    data class Params(val newPassword: String, val newPasswordConfirmation: String, val oldPassword: String)
}