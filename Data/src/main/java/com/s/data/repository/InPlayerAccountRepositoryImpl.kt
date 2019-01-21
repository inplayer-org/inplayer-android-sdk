package com.s.data.repository

import com.s.data.model.account.InPlayerAuthorizationModel
import com.s.data.model.mapper.MapInPlayerUser
import com.s.data.repository.gateway.AccountRemote
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.domain.entity.account.CredentialsEntity
import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.gateway.InPlayerAccountRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class InPlayerAccountRepositoryImpl constructor(
        private val accountRemote: AccountRemote,
        private val userLocalAuthenticator: UserLocalAuthenticator,
        private val mapInPlayerUser: MapInPlayerUser
) : InPlayerAccountRepository {
    
    /**
     *  Creating Users and handling Authorization
     * */
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?): Single<InPlayerDomainUser> {
        return accountRemote
                .createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID, referrer,metadata)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    override fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<InPlayerDomainUser> {
        return accountRemote
                .authenticateUser(username, password, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    override fun logout(): Completable {
        return accountRemote
                .logOut()
                .doOnSuccess {
                    cleanLocalPrefs()
                }.toCompletable()
    }
    
    override fun isUserAuthenticated() = userLocalAuthenticator.isUserAutehnticated()
    
    override fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<InPlayerDomainUser> {
        return accountRemote.refreshToken(refreshToken, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapInPlayerUser.mapFromModel(it.account) }
        
    }
    
    override fun clientCredentialsAuthentication(clientSecret: String, grantType: String, clientId: String): Single<InPlayerDomainUser> {
        return accountRemote.authenticateWithClientSecret(clientSecret = clientSecret, grantType = grantType, clientId = clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapInPlayerUser.mapFromModel(it.account) }
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