package com.s.inplayer.api

import com.s.domain.entity.GrantType
import com.s.domain.entity.InPlayerUser
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.AuthenticateUserUseCase
import com.s.domain.usecase.autehntication.CreateAccountUseCase
import com.s.domain.usecase.autehntication.IsUserAuthenticatedUseCase
import com.s.inplayer.callback.InPlayerCallback

/**
 * Created by victor on 12/24/18
 */
class Account(val appSchedulers: MySchedulers,
              val createAccountUseCase: CreateAccountUseCase,
              val authenticatedUseCase: AuthenticateUserUseCase,
              val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase) {
    
    
    /**
     * UseCases
     * */
    
    //val appSchedulers: MySchedulers by inject()
    
    //val authenticatedUseCase: AuthenticateUserUseCase by inject()
    
    
    //    val createAccountUseCase: CreateAccountUseCase by inject()
//
    enum class AccountType {
        CONSUMER, MERCHANT
    }
    
    /**
     * Account Interface
     * */
    
    fun signUp(fullName: String, email: String, password: String, passwordConfirmation: String, type: AccountType, merchantUUID: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        val accType = com.s.domain.entity.AccountType.valueOf(type.toString())

//        createAccountUseCase.execute(CreateAccountUseCase.Params(fullName, email, password, passwordConfirmation, accType, merchantUUID))
//                .subscribeOn(appSchedulers.subscribeOn)
//                .observeOn(appSchedulers.observeOn)
//                .subscribe({
//                    callback.done(it, null)
//                }, {
//                    callback.done(null, it)
//                })
    }
    
    fun authenticate(username: String, password: String, clientId: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(username, password, GrantType.PASSWORD, clientId))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
        
    }
    
    
    fun isUserloggedIn() = isUserAuthenticatedUseCase.execute()
}






