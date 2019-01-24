package com.sdk.data.remote.refresh_token

import com.sdk.data.model.account.InPlayerAuthorizationModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface InPlayerRemoteRefreshServiceAPI {
    
    @FormUrlEncoded
    @POST("/accounts/authenticate")
    fun authenticate(
            @Field("refresh_token") refreshToken: String,
            @Field("grant_type") grantType: String,
            @Field("client_id") clientId: String
    ): Call<InPlayerAuthorizationModel>
    
}