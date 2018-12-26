package com.s.data.remote

import com.s.data.model.InPlayerAccount
import com.s.data.model.ResponseModel
import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.repository.gateway.UserRemoteAuthenticator
import io.reactivex.Single

/**
 * Created by victor on 12/21/18
 */
class UserRemoteAuthenticatiorImpl constructor(val inPlayerRemoteProvider: InPlayerRemoteProvider) : UserRemoteAuthenticator {
  
    override fun authenticateUser(username: String, password: String, grantType: String, clientId: String) = inPlayerRemoteProvider.authenticate(username, password, grantType.toLowerCase(), clientId)
    
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String) = inPlayerRemoteProvider.createAccount(fullName, email, password, passwordConfirmation, type.toLowerCase(), merchantUUID)
    
    override fun logOut(token: String): Single<ResponseModel> {
        return inPlayerRemoteProvider.logout(token)
    }
    
    
    override fun accountDetails(token: String): Single<InPlayerAccount> {
        return inPlayerRemoteProvider.getAccount(token)
    }
    
    
}
