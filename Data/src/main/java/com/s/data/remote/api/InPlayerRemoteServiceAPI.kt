package com.s.data.remote.api

import com.s.data.model.InPlayerAccount
import com.s.data.model.InPlayerAuthorizationModel
import com.s.data.model.ResponseModel
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by victor on 12/21/18
 */
interface InPlayerRemoteServiceAPI {
    
    @FormUrlEncoded
    @POST("/accounts")
    fun createAccount(
            @Field("full_name") fullName: String,
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("password_confirmation") passwordConfirmation: String,
            @Field("type") type: String,
            @Field("merchant_uuid") merchantUUID: String,
            @Field("referrer") referrer: String): Single<InPlayerAuthorizationModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/authenticate")
    fun authenticate(
            @Field("username") username: String? = null,
            @Field("password") password: String? = null,
            @Field("client_secret") clientSecret: String? = null,
            @Field("refresh_token") refreshToken: String? = null,
            @Field("grant_type") grantType: String,
            @Field("client_id") clientId: String
    ): Single<InPlayerAuthorizationModel>
    
    
    @GET("/accounts/logout")
    fun logout(@Header("Authorization") token: String): Single<ResponseModel>
    
    
    @GET("/accounts")
    fun getAccount(@Header("Authorization") token: String): Single<InPlayerAccount>
    
    @FormUrlEncoded
    @PUT("/accounts")
    fun updateAccount(@Field("full_name") fullName: String,
                      @FieldMap metadata: HashMap<String, String>?,
                      @Header("Authorization") token: String): Single<InPlayerAccount>
    
    
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/accounts/erase", hasBody = true)
    fun eraseAccount(@Field("password") eraseUserRequest: String,
                     @Header("Authorization") token: String): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/change-password")
    fun changePassword(@Field("password") password: String,
                       @Field("password_confirmation") passwordConfirmation: String,
                       @Field("old_password") oldPassword: String,
                       @Header("Authorization") token: String): Single<ResponseModel>
    
    @FormUrlEncoded
    @POST("/accounts/forgot-password")
    fun forgotPassword(@Field("merchant_uuid") merchantUUID: String,
                       @Field("email") email: String): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @HTTP(method = "PUT", path = "/accounts/forgot-password/{token}", hasBody = true)
    fun setNewPassword(@Path("token") token: String,
                       @Field("password") password: String,
                       @Field("password_confirmation") passwordConfirmation: String): Single<ResponseModel>
    
    
}