package com.sdk.inplayer.api

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.sdk.domain.entity.account.GrantType
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.account.*
import com.sdk.domain.usecase.authentication.*
import com.sdk.inplayer.R
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.account.*
import com.sdk.inplayer.model.account.InPlayerAuthorizationModel
import com.sdk.inplayer.model.account.InPlayerRegisterFields
import com.sdk.inplayer.model.account.InPlayerSocialUrls
import com.sdk.inplayer.model.account.InPlayerUser
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.service.AccountService
import com.sdk.inplayer.util.InPlayerSDKConfiguration


/**
 * An account represents a user.
 * By using this resource, you can create consumer accounts, update data for a specific customer,
 * (de)activate your account and so on.
 *
 */
//todo group usecases
@SuppressLint("CheckResult")
class Account internal constructor(
    private val context: Context,
    private val appSchedulers: InPlayerSchedulers,
    private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
    private val accountService: AccountService,
    private val inPlayerCredentialsMapper: InPlayerCredentialsMapper,
    private val inPlayerUserMapper: InPlayerUserMapper,
    private val authorizationMapper: AuthorizationModelMapper,
    private val registerFieldsMapper: RegisterFieldsMapper,
    private val socialUrlsMapper: InPlayerSocialUrlsMapper
) {
    
    
    /**
     * Get user credentials.
     *
     * Returns: User credentials. (Optional)
     * @return InPlayerCredentials
     */
    fun getCredentials() =
        inPlayerCredentialsMapper.mapFromDomain(accountService.credentialsUseCase.execute())
    
    
    /**
     * Check's if the user is authenticated
     *
     * @return Boolean
     */
    fun isAuthenticated() = accountService.isUserAuthenticatedUseCase.execute()
    
    
    /**
     * Return the authenticated user
     *
     * @return InPlayerUser? Return null if user is not authenticated
     */
    fun getAccountInfo(): InPlayerUser? {
        accountService.getAccountUseCase.execute()?.let {
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
    fun signUp(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
    ) {
        signUp(
            fullName = fullName,
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation,
            metadata = hashMapOf(),
            callback = callback
        )
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
    fun signUp(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        metadata: HashMap<String, String>? = hashMapOf(),
        callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
    ) {
        
        val accType = com.sdk.domain.entity.account.AccountType.CONSUMER
        
        accountService.createAccountUseCase.execute(
            CreateAccountUseCase.Params(
                fullName, email, password, passwordConfirmation, accType,
                inPlayerSDKConfiguration.merchantUUID, inPlayerSDKConfiguration.referrer, metadata
            )
        )
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
    fun getAccount(callback: InPlayerCallback<InPlayerUser, InPlayerException>) {
        accountService.accountDetailsUseCase.execute()
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(inPlayerUserMapper.mapFromDomain(it), null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    
    /**
     * Exports account data such as logins, payments, subscriptions, access to assets etc.
     * After invoking the request the account will receive the data in a json format via e-mail.
     *
     * @param password of the current logged user
     * */
    fun exportData(password: String, callback: InPlayerCallback<String, InPlayerException>) {
        accountService.exportAccountDataUseCase.execute(ExportAccountDataUseCase.Params(password))
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(it, null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    fun getRegisterFields(callback: InPlayerCallback<List<InPlayerRegisterFields>, InPlayerException>) {
        accountService.getRegisterFieldsUseCase.execute(
            GetRegisterFieldsUseCase.Params(
                inPlayerSDKConfiguration.merchantUUID
            )
        )
            .observeOn(appSchedulers.observeOn)
            .subscribeOn(appSchedulers.subscribeOn)
            .subscribe({
                callback.done(
                    it.map { domainItem -> registerFieldsMapper.mapFromDomain(domainItem) },
                    null
                )
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
    fun authenticate(
        username: String,
        password: String,
        callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
    ) {
        accountService.authenticatedUseCase.execute(
            AuthenticateUserUseCase.Params(
                username = username,
                password = password,
                grantType = GrantType.PASSWORD,
                clientId = inPlayerSDKConfiguration.merchantUUID
            )
        )
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
    fun signOut(callback: InPlayerCallback<String?, InPlayerException>) {
        accountService.logOutUserUseCase.execute()
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
    fun updateAccount(
        fullName: String,
        metadata: HashMap<String, String>? = null,
        callback: InPlayerCallback<InPlayerUser, InPlayerException>
    ) {
        accountService.updateUserUseCase.execute(
            UpdateUserUseCase.Params(
                fullName = fullName,
                metadata = metadata
            )
        )
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
    fun changePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String,
        callback: InPlayerCallback<String?, InPlayerException>
    ) {
        accountService.changePasswordUseCase.execute(
            ChangePasswordUseCase.Params(
                newPassword = newPassword,
                newPasswordConfirmation = newPasswordConfirmation,
                oldPassword = oldPassword
            )
        )
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
    fun refreshToken(
        refreshToken: String,
        callback: InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>
    ) {
        accountService.authenticatedUseCase.execute(
            AuthenticateUserUseCase.Params(
                refreshToken = refreshToken,
                grantType = GrantType.REFRESH_TOKEN,
                clientId = inPlayerSDKConfiguration.merchantUUID
            )
        )
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
    fun requestNewPassword(email: String, callback: InPlayerCallback<String?, InPlayerException>) {
        accountService.forgotPasswordUseCase.execute(
            ForgotPasswordUseCase.Params(
                inPlayerSDKConfiguration.merchantUUID,
                email
            )
        )
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
    fun deleteAccount(password: String, callback: InPlayerCallback<String?, InPlayerException>) {
        accountService.eraseUserUseCase.execute(EraseUserUseCase.Params(password))
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
    fun setNewPassword(
        token: String,
        newPassword: String,
        newPasswordConfirmation: String,
        callback: InPlayerCallback<String?, InPlayerException>
    ) {
        accountService.setNewPasswordUseCase.execute(
            SetNewPasswordUseCase.Params(
                token,
                newPassword,
                newPasswordConfirmation
            )
        )
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(it, null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    
    /**
     * Gets the social login urls for fb/twitter/google
     *
     * @param callback InPlayerCallback<ArrayList<HashMap<String, String>>, InPlayerException>
     * */
    fun getSocialLoginUrls(
        redirectUri: String,
        callback: InPlayerCallback<ArrayList<InPlayerSocialUrls>, InPlayerException>
    ) {
        accountService.getSocialUrlsUseCase.execute(
            GetSocialUrlsUseCase.Params(
                clientId = inPlayerSDKConfiguration.merchantUUID,
                redirectUri = redirectUri
            )
        )
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(it.map { socialUrlsMapper.mapFromDomain(it) } as ArrayList<InPlayerSocialUrls>?,
                    null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    
    /**
     * Validate the tokens retrived from the Redirect after successfull Social authentication
     *
     * @param tokenUri: Uri The Uri containing the Token and Refresh token, retried from intent data
     * @param callback: InPlayerCallback<InPlayerUser, InPlayerException>
     * */
    fun validateSocialLoginToken(
        tokenUri: Uri,
        callback: InPlayerCallback<InPlayerUser, InPlayerException>
    ) {
        accountService.validateSocialLoginUseCase.execute(ValidateSocialLoginUseCase.Params(tokenUri.toString()))
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(inPlayerUserMapper.mapFromDomain(it), null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    /**
     * Checks validity of pin code
     *
     * @param pinCode: The pin code that was sent to your email
     * @param callback: InPlayerCallback<String, InPlayerException>
     * */
    fun validatePinCode(pinCode: String, callback: InPlayerCallback<String?, InPlayerException>) {
        accountService.pinCodeVerificationUseCase.execute(
            PinCodeVerificationUseCase.PinCodeInput.ValidatePinCode(
                pinCode
            )
        )
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(context.getString(R.string.pin_code_is_valid), null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    /**
     * Creates pin code and sends it to your email
     *
     * @param brandingID: Optional parameter
     * @param callback: InPlayerCallback<String, InPlayerException>
     * */
    
    fun sendPinCode(callback: InPlayerCallback<String?, InPlayerException>) {
        sendPinCode(null, callback)
    }
    
    fun sendPinCode(
        brandingId: String? = null,
        callback: InPlayerCallback<String?, InPlayerException>
    ) {
        accountService.pinCodeVerificationUseCase.execute(
            PinCodeVerificationUseCase.PinCodeInput.SendPinCode(
                brandingId
            )
        )
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(context.getString(R.string.check_mail_for_code), null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
}






