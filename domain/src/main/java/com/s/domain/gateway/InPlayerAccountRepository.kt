package com.s.domain.gateway

import com.s.domain.entity.account.CredentialsEntity
import com.s.domain.entity.account.InPlayerDomainUser
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
interface InPlayerAccountRepository {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?): Single<InPlayerDomainUser>
    
    fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<InPlayerDomainUser>
    
    fun logout(): Completable
    
    fun isUserAuthenticated(): Boolean
    
    fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<InPlayerDomainUser>
    
    fun clientCredentialsAuthentication(clientSecret: String, grantType: String, clientId: String): Single<InPlayerDomainUser>
    
    fun getUser(): Single<InPlayerDomainUser>
    
    fun updateUser(fullName: String, metadata: HashMap<String, String>? = null): Single<InPlayerDomainUser>
    
    fun eraseUser(password: String): Single<String>
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String): Single<String>
    
    fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<String>
    
    fun requestForgotPassword(merchantUUID: String, email: String): Single<String>
    
    fun getUserCredentials(): CredentialsEntity
}