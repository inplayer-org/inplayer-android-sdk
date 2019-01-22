package com.sdk.data.repository

import com.sdk.data.model.account.InPlayerAuthorizationModel
import com.sdk.data.model.mapper.MapAuthorizationModel
import com.sdk.data.model.mapper.MapInPlayerUser
import com.sdk.data.repository.gateway.AccountRemote
import com.sdk.data.repository.gateway.UserLocalAuthenticator
import com.sdk.domain.entity.account.AuthorizationHolder
import com.sdk.domain.entity.account.CredentialsEntity
import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class InPlayerAccountRepositoryImpl constructor(
        private val accountRemote: AccountRemote,
        private val userLocalAuthenticator: UserLocalAuthenticator,
        private val mapInPlayerUser: MapInPlayerUser,
        private val mapAuthorizationModel: MapAuthorizationModel
) : InPlayerAccountRepository {
   
    
    /**
     *  Creating Users and handling Authorization
     * */
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?): Single<AuthorizationHolder> {
        return accountRemote
                .createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID, referrer,metadata)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    override fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<AuthorizationHolder> {
        return accountRemote
                .authenticateUser(username, password, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    override fun logout(): Completable {
        return accountRemote
                .logOut()
                .doOnSuccess {
                    cleanLocalPrefs()
                }.toCompletable()
    }
    
    override fun isUserAuthenticated() = userLocalAuthenticator.isUserAutehnticated()
    
    override fun authenticatedUserAccount(): InPlayerDomainUser? {
        userLocalAuthenticator.getAccount()?.let {
            return mapInPlayerUser.mapFromModel(it)
        }
        return null
    }
    
    override fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<AuthorizationHolder> {
        return accountRemote.refreshToken(refreshToken, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapAuthorizationModel.mapFromModel(it) }
        
    }
    
    override fun clientCredentialsAuthentication(clientSecret: String, grantType: String, clientId: String): Single<AuthorizationHolder> {
        return accountRemote.authenticateWithClientSecret(clientSecret = clientSecret, grantType = grantType, clientId = clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    private fun updateLocalTokens(it: InPlayerAuthorizationModel) {
        it.refreshToken?.let {
            userLocalAuthenticator.saveRefreshToken(it)
        }
        
        it.account.let {
            userLocalAuthenticator.saveCurrentUser(it)
        }
        
        userLocalAuthenticator.saveAuthenticationToken(it.accessToken)
    }
    
    private fun cleanLocalPrefs() {
        userLocalAuthenticator.deleteRefreshToken()
        userLocalAuthenticator.deleteAuthentiationToken()
        userLocalAuthenticator.deleteCurrentUser()
    }
    
    
    override fun getUserCredentials(): CredentialsEntity {
        return CredentialsEntity(accessToken = userLocalAuthenticator.getAuthenticationToken(), refreshToken = userLocalAuthenticator.getRefreshToken())
    }
    /**
     * END Creating Users and handling Authorization
     * */
    
    
    /**
     * Account data
     * */
    
    override fun getUser(): Single<InPlayerDomainUser> {
        return accountRemote
                .accountDetails()
                .map { mapInPlayerUser.mapFromModel(it) }
    }
    
    override fun updateUser(fullName: String, metadata: HashMap<String, String>?): Single<InPlayerDomainUser> {
        return accountRemote.updateAccount(fullName, metadata)
                .map { mapInPlayerUser.mapFromModel(it) }
    }
    
    override fun eraseUser(password: String): Single<String> {
        return accountRemote
                .eraseUser(password)
                .doOnSuccess {
                    cleanLocalPrefs()
                }.map {
                    it.message
                }
    }
    
    override fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String): Single<String> {
        return accountRemote
                .changePassword(newPassword, newPasswordConfirmation, oldPassword)
                .map { it.explain }
    }
    
    override fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<String> {
        return accountRemote.setNewPassword(token, password, passwordConfirmation)
                .flatMap {
                    return@flatMap Single.just("Password Updated")
                }
    }
    
    override fun requestForgotPassword(merchantUUID: String, email: String): Single<String> {
        return accountRemote.forgotPassword(merchantUUID, email)
                .map { it.explain }
    }
    
    /**
     * END Account data
     * */
    
}