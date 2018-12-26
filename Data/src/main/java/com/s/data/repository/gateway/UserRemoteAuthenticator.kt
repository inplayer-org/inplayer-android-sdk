package com.s.data.repository.gateway

import com.s.data.model.InPlayerAccount
import com.s.data.model.InPlayerAuthorizationModel
import com.s.data.model.ResponseModel
import io.reactivex.Single

/**
 * Created by victor on 12/21/18
 */
interface UserRemoteAuthenticator {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String): Single<InPlayerAuthorizationModel>
    
    fun authenticateUser(username: String, password: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun logOut(token: String): Single<ResponseModel>
    
    fun accountDetails(token: String): Single<InPlayerAccount>
}