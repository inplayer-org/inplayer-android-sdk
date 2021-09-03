package com.sdk.domain.usecase.authentication

import com.sdk.domain.entity.account.AccountType
import com.sdk.domain.entity.account.AuthorizationHolder
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class CreateAccountUseCase constructor(schedulers: InPlayerSchedulers,
                                       private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<AuthorizationHolder, CreateAccountUseCase.Params>(schedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<AuthorizationHolder> {
        
        params?.let {
            
            return inPlayerAuthenticatorRepository.createAccount(it.fullName, it.email, it.password, it.passwordConfirmation, it.accType.toString(), it.merchantUUID, it.referrer, it.metadata, it.brandingId)
            
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
    }
    
    
    data class Params(val fullName: String, val email: String, val password: String,
                      val passwordConfirmation: String, val accType: AccountType,
                      val merchantUUID: String, val referrer: String?, val metadata: HashMap<String, String>?, val brandingId: Int? = null)
    
}