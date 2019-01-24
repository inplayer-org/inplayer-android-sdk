package com.sdk.domain.usecase.authentication

import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class UpdateUserUseCase constructor(private val appSchedulers: InPlayerSchedulers,
                                    private val inPlayerAccountRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerDomainUser, UpdateUserUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerDomainUser> {
        
        params?.let {
            return inPlayerAccountRepository.updateUser(it.fullName, it.metadata)
        }
        
        throw IllegalStateException("Params Can't be null for UpdateUserUseCase")
    }
    
    
    data class Params(val fullName: String, val metadata: HashMap<String, String>? = null)
}