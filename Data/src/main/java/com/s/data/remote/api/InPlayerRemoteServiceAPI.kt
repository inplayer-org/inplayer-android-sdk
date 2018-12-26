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
            @Field("merchant_uuid") merchantUUID: String): Single<InPlayerAuthorizationModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/authenticate")
    fun authenticate(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("grant_type") grantType: String,
            @Field("client_id") clientId: String
    ): Single<InPlayerAuthorizationModel>
    
    
    @GET("/accounts/logout")
    fun logout(@Header("Authorization") token: String): Single<ResponseModel>
    
    @GET("/accounts")
    fun getAccount(@Header("Authorization") token: String): Single<InPlayerAccount>
    
    
    @PUT("/accounts")
    fun updateAccount(): Single<InPlayerAuthorizationModel>
    
    
    @DELETE("/accounts/erase")
    fun eraseAccount(): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/change-password")
    fun changePassword(@Field("password") password: String,
                       @Field("password_confirmation") passwordConfirmation: String,
                       @Field("old_password") oldPassword: String): Single<ResponseModel>
    
    
}