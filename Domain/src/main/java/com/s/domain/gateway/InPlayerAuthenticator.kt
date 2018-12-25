package com.s.domain.gateway

import com.s.domain.entity.InPlayerUser
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
interface InPlayerAuthenticator {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String): Single<InPlayerUser>
    
    fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<InPlayerUser>
    
    fun logout(): Completable
    
    fun isUserAuthenticated(): Boolean
    
    fun getUser(): Single<InPlayerUser>
    
    fun changePassword(): Completable
    
    fun requestForgotPassword(): Completable
}