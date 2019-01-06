package com.s.data.repository.gateway

import com.s.data.model.account.InPlayerAccount
import com.s.data.model.account.InPlayerAuthorizationModel
import com.s.data.model.ResponseModel
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by victor on 12/21/18
 */
interface UserRemoteAuthenticator {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String): Single<InPlayerAuthorizationModel>
    
    fun authenticateUser(username: String, password: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun authenticateWithClientSecret(clientSecret: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun logOut(token: String): Single<ResponseModel>
    
    fun accountDetails(token: String): Single<InPlayerAccount>
    
    fun eraseUser(password: String, token: String): Single<ResponseModel>
    
    fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<Response<Void>>
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String, token: String): Single<ResponseModel>
    
    fun forgotPassword(merchantUUID: String, email: String): Single<ResponseModel>
    
    fun updateAccount(fullName: String, metadata: HashMap<String, String>? = null, token: String): Single<InPlayerAccount>
}