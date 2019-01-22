package com.sdk.domain.gateway

import com.sdk.domain.entity.account.AuthorizationHolder
import com.sdk.domain.entity.account.CredentialsEntity
import com.sdk.domain.entity.account.InPlayerDomainUser
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
interface InPlayerAccountRepository {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?): Single<AuthorizationHolder>
    
    fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<AuthorizationHolder>
    
    fun logout(): Completable
    
    fun isUserAuthenticated(): Boolean
    
    fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<AuthorizationHolder>
    
    fun clientCredentialsAuthentication(clientSecret: String, grantType: String, clientId: String): Single<AuthorizationHolder>
    
    fun getUser(): Single<InPlayerDomainUser>
    
    fun updateUser(fullName: String, metadata: HashMap<String, String>? = null): Single<InPlayerDomainUser>
    
    fun eraseUser(password: String): Single<String>
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String): Single<String>
    
    fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<String>
    
    fun requestForgotPassword(merchantUUID: String, email: String): Single<String>
    
    fun getUserCredentials(): CredentialsEntity
}