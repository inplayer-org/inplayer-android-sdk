package com.s.data.remote.refresh_token

import com.s.data.model.InPlayerAuthorizationModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by victor on 1/3/19
 */
interface InPlayerRemoteRefreshServiceAPI {
    
    @FormUrlEncoded
    @POST("/accounts/authenticate")
    fun authenticate(
            @Field("refresh_token") refreshToken: String,
            @Field("grant_type") grantType: String,
            @Field("client_id") clientId: String
    ): Call<InPlayerAuthorizationModel>
    
}