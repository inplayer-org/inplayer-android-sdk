package com.s.inplayer.api

import com.s.domain.entity.account.GrantType
import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.entity.mapper.DomainMapper
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.*
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.callback.InPlayerCallback
import com.s.inplayer.model.error.InPlayerException
import com.s.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.s.inplayer.model.InPlayerUser

/**
 * Created by victor on 12/24/18
 */
class Account(private val appSchedulers: MySchedulers,
              private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
              private val domainMapper: DomainMapper<InPlayerDomainUser, InPlayerUser>,
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
    
    
    /**
     * Account Interface
     * */
    
    fun isAuthenticated() = isUserAuthenticatedUseCase.execute()
    
    //TODO ADD METADATA
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        
        val accType = com.s.domain.entity.account.AccountType.CONSUMER
        
        createAccountUseCase.execute(CreateAccountUseCase.Params(fullName, email, password, passwordConfirmation, accType,
                inPlayerSDKConfiguration.merchantUUID, inPlayerSDKConfiguration.referrer))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun getAccountDetails(callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        accountDetailsUseCase.execute()
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun authenticate(username: String, password: String, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(username = username, password = password, grantType = GrantType.PASSWORD, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
        
    }
    
    fun logout(callback: InPlayerCallback<String?, InPlayerException>) {
        logOutUserUseCase.execute()
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done("User Logged out", null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun updateAccount(fullName: String, metadata: HashMap<String, String>? = null, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        updateUserUseCase.execute(UpdateUserUseCase.Params(fullName, metadata = metadata))
                .subscribeOn(appSchedulers.observeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String, callback: InPlayerCallback<String?, InPlayerException>) {
        changePasswordUseCase.execute(ChangePasswordUseCase.Params(newPassword, newPasswordConfirmation, oldPassword))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun refreshAccessToken(refreshToken: String, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(refreshToken = refreshToken, grantType = GrantType.REFRESH_TOKEN, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun authenticateWithUser(clientSecret: String, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(clientSecret = clientSecret, grantType = GrantType.CLIENT_CREDENTIALS, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(domainMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    
    fun forgotPassword(email: String, callback: InPlayerCallback<String?, InPlayerException>) {
        forgotPasswordUseCase.execute(ForgotPasswordUseCase.Params(inPlayerSDKConfiguration.merchantUUID, email))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    
    fun eraseAccount(password: String, callback: InPlayerCallback<String?, InPlayerException>) {
        eraseUserUseCase.execute(EraseUserUseCase.Params(password))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    
    fun setNewPassword(token: String, newPassword: String, newPasswordConfirmation: String, callback: InPlayerCallback<String?, InPlayerException>) {
        setNewPasswordUseCase.execute(SetNewPasswordUseCase.Params(token, newPassword, newPasswordConfirmation))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}






