package com.s.data.repository

import com.s.data.model.mapper.MapInPlayerUser
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.data.repository.gateway.UserRemoteAuthenticator
import com.s.domain.entity.InPlayerUser
import com.s.domain.gateway.InPlayerAuthenticator
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class InPlayerAuthenticatorImpl constructor(
        val userRemoteAuthenticator: UserRemoteAuthenticator,
        val userLocalAuthenticator: UserLocalAuthenticator,
        val mapInPlayerUser: MapInPlayerUser
) : InPlayerAuthenticator {
    
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String): Single<InPlayerUser> {
        return userRemoteAuthenticator.createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID)
                .doOnSuccess {
                    userLocalAuthenticator.saveAuthenticationToken(it.accessToken)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    override fun autehenticate(username: String, password: String, grantType: String, clientId: String): Single<InPlayerUser> {
        return userRemoteAuthenticator.authenticateUser(username, password, grantType, clientId)
                .doOnSuccess {
                    userLocalAuthenticator.saveAuthenticationToken(it.accessToken)
                }
                .map { mapInPlayerUser.mapFromModel(it.account) }
    }
    
    
    override fun logout(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    override fun isUserAuthenticated() = userLocalAuthenticator.isUserAutehnticated()
    
    override fun getUser(): Single<InPlayerUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    override fun changePassword(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    override fun requestForgotPassword(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}