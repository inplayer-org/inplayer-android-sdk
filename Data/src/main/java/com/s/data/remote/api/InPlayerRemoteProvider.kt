package com.s.data.remote.api

import com.s.data.remote.interceptor.RefreshAuthenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by victor on 12/21/18
 */
class InPlayerRemoteProvider(private val baseUrl: String,
                             private val isDebug: Boolean,
                             val refreshAuthenticator: RefreshAuthenticator) : InPlayerAccountRemoteServiceAPI {
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    private lateinit var inPlayerRemoteServiceAPI: InPlayerAccountRemoteServiceAPI
    
    init {
        makeInPlayerRemoteService()
    }
    
    private fun makeInPlayerRemoteService(): InPlayerAccountRemoteServiceAPI {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        inPlayerRemoteServiceAPI = buildService(okHttpClient)
        return inPlayerRemoteServiceAPI
    }
    
    private fun buildService(okHttpClient: OkHttpClient): InPlayerAccountRemoteServiceAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(InPlayerAccountRemoteServiceAPI::class.java)
    }
    
    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
    
    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor {
                    customHeaderIntercepted(it)
                }
                .authenticator(refreshAuthenticator)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }
    
    private fun customHeaderIntercepted(it: Interceptor.Chain): Response? {
        val original = it.request()
        
        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
        
        val request = requestBuilder.build()
        return it.proceed(request)
    }
    
    /**
     * END -> Creating Retrofit and setting up Logging
     * */
    
    
    /**
     * ACCOUNT Endpoint Implementations
     * */
    
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String) = inPlayerRemoteServiceAPI.createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID, referrer)
    
    override fun authenticate(username: String?, password: String?, clientSecret: String?, refreshToken: String?, grantType: String, clientId: String) = inPlayerRemoteServiceAPI.authenticate(username, password, clientSecret, refreshToken, grantType, clientId)
    
    override fun logout(token: String) = inPlayerRemoteServiceAPI.logout(token)
    
    override fun getAccount(token: String) = inPlayerRemoteServiceAPI.getAccount(token)
    
    override fun updateAccount(fullName: String, metadata: HashMap<String, String>?, token: String) = inPlayerRemoteServiceAPI.updateAccount(fullName, metadata, token)
    
    override fun eraseAccount(password: String, token: String) = inPlayerRemoteServiceAPI.eraseAccount(password, token)
    
    override fun changePassword(password: String, passwordConfirmation: String, oldPassword: String, token: String) = inPlayerRemoteServiceAPI.changePassword(password, passwordConfirmation, oldPassword, token)
    
    override fun setNewPassword(token: String, password: String, passwordConfirmation: String) = inPlayerRemoteServiceAPI.setNewPassword(token, password, passwordConfirmation)
    
    override fun forgotPassword(merchantUUID: String, email: String) = inPlayerRemoteServiceAPI.forgotPassword(merchantUUID, email)
  
    /**
     * END -> ACCOUNT Endpoint Implementations
     * */
    
    
    /**
     * ASSETS Endpoint Implementations
     * */
    
    override fun getItemAccess(id: Int, token: String) = inPlayerRemoteServiceAPI.getItemAccess(id, token)
    
    override fun getItemDetails(id: Int, merchantUUID: String) = inPlayerRemoteServiceAPI.getItemDetails(id, merchantUUID)
    
    override fun getAccessFees(id: Int) = inPlayerRemoteServiceAPI.getAccessFees(id)
    
    /**
     * END -> ASSETS Endpoint Implementations
     * */
}