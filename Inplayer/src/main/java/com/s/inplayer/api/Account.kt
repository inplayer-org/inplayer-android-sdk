package com.s.inplayer.api

import com.s.domain.entity.GrantType
import com.s.domain.entity.InPlayerUser
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.*
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.callback.InPlayerCallback

/**
 * Created by victor on 12/24/18
 */
class Account(private val appSchedulers: MySchedulers,
              private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
              private val createAccountUseCase: CreateAccountUseCase,
              private val authenticatedUseCase: AuthenticateUserUseCase,
              private val logOutUserUseCase: LogOutUserUseCase,
              private val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
              private val accountDetailsUseCase: AccountDetailsUseCase,
              private val eraseUserUseCase: EraseUserUseCase,
              private val changePasswordUseCase: ChangePasswordUseCase,
              private val forgotPasswordUseCase: ForgotPasswordUseCase,
              private val updateUserUseCase: UpdateUserUseCase,
              private val setNewPasswordUseCase: SetNewPasswordUseCase) {
    
    enum class AccountType {
        CONSUMER, MERCHANT
    }
    
    /**
     * Account Interface
     * */
    
    fun signUp(fullName: String, email: String, password: String, passwordConfirmation: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        val accType = com.s.domain.entity.AccountType.CONSUMER
        
        createAccountUseCase.execute(CreateAccountUseCase.Params(fullName, email, password, passwordConfirmation, accType,
                inPlayerSDKConfiguration.merchantUUID, inPlayerSDKConfiguration.referrer))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    fun authenticate(username: String, password: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(username = username, password = password, grantType = GrantType.PASSWORD, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
        
    }
    
    fun refreshToken(refreshToken: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(refreshToken = refreshToken, grantType = GrantType.REFRESH_TOKEN, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    fun authenticateWithUser(clientSecret: String, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(clientSecret = clientSecret, grantType = GrantType.CLIENT_CREDENTIALS, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    
    fun isUserloggedIn() = isUserAuthenticatedUseCase.execute()
    
    fun logOut(callback: InPlayerCallback<String?, Throwable>) {
        logOutUserUseCase.execute()
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done("User Logged out", null)
                }, {
                    callback.done(null, it)
                })
    }
    
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String, callback: InPlayerCallback<String?, Throwable>) {
        changePasswordUseCase.execute(ChangePasswordUseCase.Params(newPassword, newPasswordConfirmation, oldPassword))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    fun forgotPassword(merchantUUID: String, email: String, callback: InPlayerCallback<String?, Throwable>) {
        forgotPasswordUseCase.execute(ForgotPasswordUseCase.Params(merchantUUID, email))
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
    
    
    fun eraseUser(password: String, callback: InPlayerCallback<String?, Throwable>) {
        eraseUserUseCase.execute(EraseUserUseCase.Params(password))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    fun updateUser(fullName: String, metadata: HashMap<String, String>? = null, callback: InPlayerCallback<InPlayerUser, Throwable>) {
        updateUserUseCase.execute(UpdateUserUseCase.Params(fullName, metadata = metadata))
                .subscribeOn(appSchedulers.observeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
    
    fun setupNewPassword(token: String, newPassword: String, newPasswordConfirmation: String, callback: InPlayerCallback<String?, Throwable>) {
        setNewPasswordUseCase.execute(SetNewPasswordUseCase.Params(token, newPassword, newPasswordConfirmation))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, it)
                })
    }
}






