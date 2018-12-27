package com.s.domain.usecase.autehntication

import com.s.domain.entity.AccountType
import com.s.domain.entity.InPlayerUser
import com.s.domain.exception.InPlayerInvalidFieldsException
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.isValidEmail
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class CreateAccountUseCase constructor(schedulers: MySchedulers,
                                       private val inPlayerAuthenticatorRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerUser, CreateAccountUseCase.Params>(schedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerUser> {
        
        params?.let {
            
            if (!it.email.isValidEmail())
                return Single.error(InPlayerInvalidFieldsException("Email is not in valid format"))
            
            if (it.password != it.passwordConfirmation)
                return Single.error(InPlayerInvalidFieldsException("Password does not match Password Confirmation"))
            
            return inPlayerAuthenticatorRepository.createAccount(it.fullName, it.email, it.password, it.passwordConfirmation, it.accType.toString(), it.merchantUUID)
            
        }
        
        throw IllegalStateException("Params Can't be null for CreateAccountUseCase")
    }
    
    
    data class Params(val fullName: String, val email: String, val password: String,
                      val passwordConfirmation: String, val accType: AccountType,
                      val merchantUUID: String, val referrer: String? = null)
    
}