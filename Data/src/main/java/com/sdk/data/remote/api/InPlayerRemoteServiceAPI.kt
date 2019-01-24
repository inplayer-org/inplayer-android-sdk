package com.sdk.data.remote.api

import com.sdk.data.model.ResponseModel
import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.data.model.notification.AWSCredentialsModel
import io.reactivex.Single
import retrofit2.http.*

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
    
    /**
     *  Notifications
     * */
    
    @GET
    fun getAwsCredentials(@Url url: String): Single<AWSCredentialsModel>
    
    /**
     * END Notifications
     * */
    
    
    /**
     *  Payments
     * */
    
    @POST("/v2/external-payments/android/validate")
    fun validateAndroidReceipt(@Field("receipt") receipt: String,
                               @Field("item_id") item_id: Int,
                               @Field("access_fee_id") access_fee_id: Int): Single<ResponseModel>
    
    /**
     * END Payments
     * */
    
    
}