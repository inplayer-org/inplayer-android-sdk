package com.s.data.remote.api

import com.s.data.model.ResponseModel
import com.s.data.model.account.InPlayerAccount
import com.s.domain.entity.asset.ItemAccessModel
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by victor on 12/21/18
 */
interface InPlayerRemoteServiceAPI {
    
    
    /**
     * ACCOUNT Endpoint
     * */
    
    @GET("/accounts/logout")
    fun logout(): Single<ResponseModel>
    
    
    @GET("/accounts")
    fun getAccount(): Single<InPlayerAccount>
    
    @FormUrlEncoded
    @PUT("/accounts")
    fun updateAccount(@Field("full_name") fullName: String,
                      @FieldMap metadata: HashMap<String, String>?
    ): Single<InPlayerAccount>
    
    
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/accounts/erase", hasBody = true)
    fun eraseAccount(@Field("password") password: String
    ): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/change-password")
    fun changePassword(@Field("password") password: String,
                       @Field("password_confirmation") passwordConfirmation: String,
                       @Field("old_password") oldPassword: String): Single<ResponseModel>
    
    
    /**
     * END ACCOUNT Endpoint
     * */
    
    /**
     * ASSETS Endpoint
     * */
    
    @GET("/items/{id}/access")
    fun getItemAccess(@Path("id") id: Int): Single<ItemAccessModel>
    
    
    /**
     * END ASSETS Endpoint
     * */
    
}