package com.sdk.data.repository.gateway

import com.sdk.data.model.ResponseModel
import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.model.account.InPlayerAuthorizationModel
import com.sdk.data.model.account.InPlayerRegisterFieldsModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response


interface AccountRemote {
    
    fun createAccount(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        type: String,
        merchantUUID: String,
        referrer: String?,
        metadata: HashMap<String, String>?,
        brandingId: String?
    ): Single<InPlayerAuthorizationModel>
    
    fun authenticateUser(
        username: String,
        password: String,
        grantType: String,
        clientId: String
    ): Single<InPlayerAuthorizationModel>
    
    fun refreshToken(
        refreshToken: String,
        grantType: String,
        clientId: String
    ): Single<InPlayerAuthorizationModel>
    
    fun authenticateWithClientSecret(
        clientSecret: String,
        grantType: String,
        clientId: String
    ): Single<InPlayerAuthorizationModel>
    
    fun forgotPassword(merchantUUID: String, email: String, brandingId: String? = null): Single<ResponseModel>
    
    fun setNewPassword(
        token: String,
        password: String,
        passwordConfirmation: String,
        brandingId: String? = null
    ): Completable
    
    fun getRegisterFields(merchantUUID: String): Single<List<InPlayerRegisterFieldsModel>>
    
    fun logOut(): Single<ResponseModel>
    
    fun accountDetails(): Single<InPlayerAccount>
    
    fun exportUserData(password: String, brandingId: String?): Single<ResponseModel>
    
    fun eraseUser(password: String, brandingId: String? = null): Single<ResponseModel>
    
    fun changePassword(
        newPassword: String,
        newPasswordConfirmation: String,
        oldPassword: String,
        brandingId: String? = null
    ): Single<ResponseModel>
    
    fun updateAccount(
        fullName: String,
        metadata: HashMap<String, String>? = null
    ): Single<InPlayerAccount>
    
    fun getSocialUrls(state: String): Single<ArrayList<HashMap<String, String>>>
    
    fun validatePinCode(pinCode: String): Completable
    
    fun sendPinCode(brandingId: String? = null): Completable
}