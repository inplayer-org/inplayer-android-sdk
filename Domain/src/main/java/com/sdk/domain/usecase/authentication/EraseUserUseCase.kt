package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class EraseUserUseCase constructor(private val inPlayerSchedulers: InPlayerSchedulers,
                                   private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<String, EraseUserUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            return inPlayerAuthenticatorRepository.eraseUser(it.password, it.brandingId)
        }
        
        throw IllegalStateException("Params can't be null for EraseUserUseCase")
    }
    
    
    data class Params(val password: String, val brandingId: String? = null)
    
}