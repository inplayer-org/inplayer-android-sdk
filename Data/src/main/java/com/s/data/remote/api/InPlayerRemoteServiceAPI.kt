package com.s.data.remote.api

import com.s.data.model.InPlayerAuthorizationModel
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by victor on 12/21/18
 */
interface InPlayerRemoteServiceAPI {
    
    @FormUrlEncoded
    @POST("/accounts")
    fun createAccount(
            @Field("email") email: String,
            @Field("full_name") fullName: String,
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
    
}