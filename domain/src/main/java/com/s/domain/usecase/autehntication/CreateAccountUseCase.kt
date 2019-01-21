package com.s.domain.usecase.autehntication

import com.s.domain.entity.account.AccountType
import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class CreateAccountUseCase constructor(schedulers: MySchedulers,
                                       private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerDomainUser, CreateAccountUseCase.Params>(schedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerDomainUser> {
        
        params?.let {
            
            return inPlayerAuthenticatorRepository.createAccount(it.fullName, it.email, it.password, it.passwordConfirmation, it.accType.toString(), it.merchantUUID, it.referrer, it.metadata)
            
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
    }
    
    
    data class Params(val fullName: String, val email: String, val password: String,
                      val passwordConfirmation: String, val accType: AccountType,
                      val merchantUUID: String, val referrer: String, val metadata: HashMap<String, String>?)
    
}