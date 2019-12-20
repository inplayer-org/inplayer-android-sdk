package com.sdk.data.remote.api

import com.sdk.data.model.CollectionModel
import com.sdk.data.model.ResponseListModel
import com.sdk.data.model.ResponseModel
import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.model.account.InPlayerRegisterFieldsModel
import com.sdk.data.model.account.InPlayerSocialUrlsReponse
import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.data.model.notification.AWSCredentialsModel
import com.sdk.data.model.payment.CustomerAccessItemModel
import com.sdk.data.model.subscription.SubscriptionModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

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
    fun updateAccount(
        @Field("full_name") fullName: String,
        @FieldMap metadata: HashMap<String, String>?
    ): Single<InPlayerAccount>
    
    
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/accounts/erase", hasBody = true)
    fun eraseAccount(
        @Field("password") password: String
    ): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/change-password")
    fun changePassword(
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
        @Field("old_password") oldPassword: String
    ): Single<ResponseModel>
    
    
    @FormUrlEncoded
    @POST("/accounts/export")
    fun exportAccountData(@Field("password") password: String): Single<ResponseModel>
    
    
    @GET("/items/access/customers")
    fun getCustomerAccessList(
        @Query("status") status: String,
        @Query("page") page: Int,
        @Query("size") limit: Int,
        @Query("type") type: String?
    ): Single<ResponseListModel<CustomerAccessItemModel>>
    
    
    @GET("/accounts/social")
    fun getSocialUrls(@Query("state") socialUrlState: String): Single<InPlayerSocialUrlsReponse>
    
    @FormUrlEncoded
    @POST("/v2/accounts/pin-codes/validate")
    fun validatePinCode(@Field("pin_code") pinCode: String): Completable
    
    @FormUrlEncoded
    @POST("/v2/accounts/pin-codes/send")
    fun sendPinCode(@Field("branding_id") brandingId: String? = null): Completable
    
    
    /**
     * END ACCOUNT Endpoint
     * */
    
    /**
     * ASSETS Endpoint
     * */
    
    @GET("/items/{id}/access")
    fun getItemAccess(@Path("id") id: Int, @Query("entry_id") entryId: String?): Single<ItemAccessModel>
    
    
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
    fun validateAndroidReceipt(
        @Field("receipt") receipt: String,
        @Field("item_id") item_id: Int,
        @Field("access_fee_id") access_fee_id: Int
    ): Single<ResponseModel>
    
    /**
     * END Payments
     * */
    
    
    /**
     * Subscription
     * */
    
    @GET("/subscriptions")
    fun getSubscriptions(@Query("page") page: Int, @Query("size") limit: Int): Single<CollectionModel<SubscriptionModel>>
    
    
    /**
     * END Subscription
     * */
    
    
}