package com.s.data.repository

import com.s.data.model.InPlayerAuthorizationModel
import com.s.data.model.mapper.MapInPlayerUser
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.data.repository.gateway.UserRemoteAuthenticator
import com.s.domain.entity.InPlayerUser
import com.s.domain.gateway.InPlayerAccountRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class InPlayerAccountRepositoryImpl constructor(
        val userRemoteAuthenticator: UserRemoteAuthenticator,
        val userLocalAuthenticator: UserLocalAuthenticator,
        val mapInPlayerUser: MapInPlayerUser
) : InPlayerAccountRepository {
    
    /**
     *  Creating Users and handling Authorization
     * */
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String): Single<InPlayerUser> {
        return userRemoteAuthenticator
                .createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID, referrer)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    override fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<InPlayerUser> {
        return userRemoteAuthenticator
                .authenticateUser(username, password, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    override fun logout(): Completable {
        return userRemoteAuthenticator
                .logOut(userLocalAuthenticator.getBearerAuthToken())
                .doOnSuccess {
                    //userLocalAuthenticator.deleteRefreshToken()
                    userLocalAuthenticator.deleteAuthentiationToken()
                }.toCompletable()
    }
    
    override fun isUserAuthenticated() = userLocalAuthenticator.isUserAutehnticated()
    
    override fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<InPlayerUser> {
        return userRemoteAuthenticator.refreshToken(refreshToken, grantType, clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapInPlayerUser.mapFromModel(it.account) }
        
    }
    
    override fun clientCredentialsAuthentication(clientSecret: String, grantType: String, clientId: String): Single<InPlayerUser> {
        return userRemoteAuthenticator.authenticateWithClientSecret(clientSecret = clientSecret, grantType = grantType, clientId = clientId)
                .doOnSuccess {
                    updateLocalTokens(it)
                }.map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    private fun updateLocalTokens(it: InPlayerAuthorizationModel) {
        it.refreshToken?.let {
            userLocalAuthenticator.saveRefreshToken(it)
        }
        userLocalAuthenticator.saveAuthenticationToken(it.accessToken)
    }
    
    /**
     * END Creating Users and handling Authorization
     * */
    
    
    /**
     * Account data
     * */
    
    override fun getUser(): Single<InPlayerUser> {
        return userRemoteAuthenticator
                .accountDetails(userLocalAuthenticator.getBearerAuthToken())
                .map { mapInPlayerUser.mapFromModel(it) }
    }
    
    override fun updateUser(fullName: String, metadata: HashMap<String, String>?): Single<InPlayerUser> {
        return userRemoteAuthenticator.updateAccount(fullName, metadata, userLocalAuthenticator.getBearerAuthToken())
                .map { mapInPlayerUser.mapFromModel(it) }
    }
    
    override fun eraseUser(password: String): Single<String> {
        return userRemoteAuthenticator
                .eraseUser(password, userLocalAuthenticator.getBearerAuthToken())
                .doOnSuccess {
                    userLocalAuthenticator.deleteAuthentiationToken()
                }.map {
                    it.message
                }
    }
    
    override fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String): Single<String> {
        return userRemoteAuthenticator
                .changePassword(newPassword, newPasswordConfirmation, oldPassword, userLocalAuthenticator.getBearerAuthToken())
                .map { it.explain }
    }
    
    override fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<String> {
        return userRemoteAuthenticator.setNewPassword(token, password, passwordConfirmation)
                .map { it.explain }
    }
    
    override fun requestForgotPassword(merchantUUID: String, email: String): Single<String> {
        return userRemoteAuthenticator.forgotPassword(merchantUUID, email)
                .map { it.explain }
    }
    
    /**
     * END Account data
     * */
    
}