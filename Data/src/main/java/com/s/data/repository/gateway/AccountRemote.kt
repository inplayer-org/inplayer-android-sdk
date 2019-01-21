package com.s.data.repository.gateway

import com.s.data.model.ResponseModel
import com.s.data.model.account.InPlayerAccount
import com.s.data.model.account.InPlayerAuthorizationModel
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by victor on 12/21/18
 */
interface AccountRemote {
    
    fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?): Single<InPlayerAuthorizationModel>
    
    fun authenticateUser(username: String, password: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun refreshToken(refreshToken: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun authenticateWithClientSecret(clientSecret: String, grantType: String, clientId: String): Single<InPlayerAuthorizationModel>
    
    fun forgotPassword(merchantUUID: String, email: String): Single<ResponseModel>
    
    fun setNewPassword(token: String, password: String, passwordConfirmation: String): Single<Response<Void>>
    
    
    fun logOut(): Single<ResponseModel>
    
    fun accountDetails(): Single<InPlayerAccount>
    
    fun eraseUser(password: String): Single<ResponseModel>
    
    fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String): Single<ResponseModel>
    
    fun updateAccount(fullName: String, metadata: HashMap<String, String>? = null): Single<InPlayerAccount>
}