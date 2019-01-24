package com.sdk.inplayer.api

import android.annotation.SuppressLint
import com.sdk.domain.entity.account.GrantType
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.authentication.AccountDetailsUseCase
import com.sdk.domain.usecase.authentication.AuthenticateUserUseCase
import com.sdk.domain.usecase.authentication.ChangePasswordUseCase
import com.sdk.domain.usecase.authentication.CreateAccountUseCase
import com.sdk.domain.usecase.authentication.CredentialsUseCase
import com.sdk.domain.usecase.authentication.EraseUserUseCase
import com.sdk.domain.usecase.authentication.ForgotPasswordUseCase
import com.sdk.domain.usecase.authentication.GetAccountUseCase
import com.sdk.domain.usecase.authentication.IsUserAuthenticatedUseCase
import com.sdk.domain.usecase.authentication.LogOutUserUseCase
import com.sdk.domain.usecase.authentication.SetNewPasswordUseCase
import com.sdk.domain.usecase.authentication.UpdateUserUseCase
import com.sdk.inplayer.util.InPlayerSDKConfiguration
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.account.AuthorizationModelMapper
import com.sdk.inplayer.mapper.account.InPlayerCredentialsMapper
import com.sdk.inplayer.mapper.account.InPlayerUserMapper
import com.sdk.inplayer.model.account.InPlayerAuthorizationModel
import com.sdk.inplayer.model.account.InPlayerUser
import com.sdk.inplayer.model.error.InPlayerException

/**
 * An account represents a user.
 * By using this resource, you can create consumer accounts, update data for a specific customer,
 * (de)activate your account and so on.
 *
 */
//todo group usecases
@SuppressLint("CheckResult")
class Account internal constructor(private val appSchedulers: InPlayerSchedulers,
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
                                   private val setNewPasswordUseCase: SetNewPasswordUseCase,
                                   private val credentialsUseCase: CredentialsUseCase,
                                   private val inPlayerCredentialsMapper: InPlayerCredentialsMapper,
                                   private val inPlayerUserMapper: InPlayerUserMapper,
                                   private val getAccountUseCase: GetAccountUseCase,
                                   private val authorizationMapper: AuthorizationModelMapper) {
    
    
    /**
     * Get user credentials.
     *
     * Returns: User credentials. (Optional)
     * @return InPlayerCredentials
     */
    fun getCredentials() = inPlayerCredentialsMapper.mapFromDomain(credentialsUseCase.execute())
    
    
    /**
     * Check's if the user is authenticated
     *
     * @return Boolean
     */
    fun isAuthenticated() = isUserAuthenticatedUseCase.execute()
    
    
    /**
     * Return the authenticated user
     *
     * @return InPlayerUser? Return null if user is not authenticated
     */
    fun getAccount() : InPlayerUser? {
        getAccountUseCase.execute()?.let {
            return inPlayerUserMapper.mapFromDomain(it)
        }
        return null
    }
    
    /**
     * Registers a new InPlayer account.
     *
     * @param fullName String Account's first and last name
     * @param email String Account’s email address
     * @param password String Password containing minimum 8 characters
     * @param passwordConfirmation String The same password with minimum 8 characters
     * @param callback InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
     */
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>) {
        createAccount(fullName = fullName, email = email, password = password, passwordConfirmation = passwordConfirmation, metadata = hashMapOf(), callback = callback)
    }
    
    
    /**
     * Registers a new InPlayer account.
     *
     * @param fullName String Account's first and last name
     * @param email String Account’s email address
     * @param password String Password containing minimum 8 characters
     * @param passwordConfirmation String The same password with minimum 8 characters
     * @param metadata HashMap<String,String> Additional information about the account that can be attached to the account object
     * @param callback InPlayerCallback<{@code InPlayerAuthorizationModel}, {@code InPlayerException}>
     */
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, metadata: HashMap<String, String>? = hashMapOf(), callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>) {
        
        val accType = com.sdk.domain.entity.account.AccountType.CONSUMER
        
        createAccountUseCase.execute(CreateAccountUseCase.Params(fullName, email, password, passwordConfirmation, accType,
                inPlayerSDKConfiguration.merchantUUID, inPlayerSDKConfiguration.referrer, metadata))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(authorizationMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Returns all the relevant information about an account.
     *
     * @param callback InPlayerCallback<InPlayerUser, InPlayerException>
     */
    fun getAccountDetails(callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        accountDetailsUseCase.execute()
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(inPlayerUserMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Authenticates an account by creating an access token that can be used for future requests.
     *
     * @param username String The email address of the account
     * @param password String The account password
     * @param callback InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
     */
    fun authenticate(username: String, password: String, callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>) {
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(username = username, password = password, grantType = GrantType.PASSWORD, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(authorizationMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
        
    }
    
    /**
     * Logs the account out.
     *
     * @param callback InPlayerCallback<String?, InPlayerException>
     */
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
    
    /**
     * Updates an account
     *
     * @param fullName String The full name of the account
     * @param metadata HashMap<String, String>? Additional information about the account that can be attached to the account object
     * @param callback InPlayerCallback<InPlayerUser, InPlayerException>
     */
    fun updateAccount(fullName: String, metadata: HashMap<String, String>? = null, callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        updateUserUseCase.execute(UpdateUserUseCase.Params(fullName, metadata = metadata))
                .subscribeOn(appSchedulers.observeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(inPlayerUserMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Replaces the account's password with a new one.
     *
     * @param newPassword String The account's new password
     * @param newPasswordConfirmation String The account's new password for confirmation.
     * @param oldPassword String Account's old password
     * @param callback InPlayerCallback<String?, InPlayerException>
     */
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
    
    /**
     *  Refreshes account using refreshToken
     *
     * @param refreshToken String An auto-generated token that enables access when the original access token has expired without
     * requiring re authentication
     * @param callback InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
     */
    fun refreshAccessToken(refreshToken: String, callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>) {
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(refreshToken = refreshToken, grantType = GrantType.REFRESH_TOKEN, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(authorizationMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Authenticates account using clientSecret
     *
     * @param clientSecret String Corresponding secret between the client and the application
     * @param callback InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
     */
    fun authenticateWithUser(clientSecret: String, callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>) {
        authenticatedUseCase.execute(AuthenticateUserUseCase.Params(clientSecret = clientSecret, grantType = GrantType.CLIENT_CREDENTIALS, clientId = inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(authorizationMapper.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Provides you with a token for resetting your password via email.
     *
     * @param email String Account’s email address
     * @param callback InPlayerCallback<String?, InPlayerException>
     */
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
    
    /**
     * Erases an account (and all the data associated) permanently.
     *
     * @param password String Password confirmation
     * @param callback InPlayerCallback<String?, InPlayerException>
     */
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
    
    /**
     * Updates your password by using the previously provided forgot password token.
     *
     * @param token String The forgot password token sent to your email address
     * @param newPassword String The account’s new password
     * @param newPasswordConfirmation String The password confirmation
     * @param callback InPlayerCallback<String?, InPlayerException>
     */
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






