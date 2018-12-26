package com.s.inplayer.api

import com.s.domain.entity.GrantType
import com.s.domain.entity.InPlayerUser
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.*
import com.s.inplayer.callback.InPlayerCallback

/**
 * Created by victor on 12/24/18
 */
class Account(val appSchedulers: MySchedulers,
              val createAccountUseCase: CreateAccountUseCase,
              val authenticatedUseCase: AuthenticateUserUseCase,
              val logOutUserUseCase: LogOutUserUseCase,
              val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
              val accountDetailsUseCase: AccountDetailsUseCase) {
    
    enum class AccountType {
        CONSUMER, MERCHANT
    }
    
    /**
     * Account Interface
     * */
    
    fun signUp(fullName: String, email: String, password: String, passwordConfirmation: String, merchantUUID: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        val accType = com.s.domain.entity.AccountType.CONSUMER
        
        createAccountUseCase.execute(CreateAccountUseCase.Params(fullName, email, password, passwordConfirmation, accType, merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
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
    
    
    fun accountDetails(callback: InPlayerCallback<InPlayerUser, Throwable>) {
        accountDetailsUseCase.execute()
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    
    fun logOut(callback: InPlayerCallback<String?, Throwable>) {
        logOutUserUseCase.execute().subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done("User Logged out", null)
                }, {
                    callback.done(null, it)
                })
    }
    
    
    fun isUserloggedIn() = isUserAuthenticatedUseCase.execute()
}






