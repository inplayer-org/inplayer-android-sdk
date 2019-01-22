package com.sdk.domain.usecase.autehntication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/27/18
 */
class SetNewPasswordUseCase(val appSchedulers: MySchedulers,
                            private val inPlayerAccountRepository: InPlayerAccountRepository) : SingleUseCase<String, SetNewPasswordUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            return inPlayerAccountRepository.setNewPassword(it.token, it.password, it.passwordConfirmation)
        }
        
        throw IllegalStateException("Params Can't be null for SetNewPasswordUseCase")
    }
    
    data class Params(val token: String, val password: String, val passwordConfirmation: String)
}