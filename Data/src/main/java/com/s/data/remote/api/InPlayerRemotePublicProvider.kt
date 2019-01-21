package com.s.data.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by victor on 1/9/19
 */
class InPlayerRemotePublicProvider(val baseUrl: String, val isDebug: Boolean) : InPlayerRemotePublicServiceAPI {
    
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    lateinit var retrofitAPI: InPlayerRemotePublicServiceAPI
    
    init {
        makeInPlayerRemoteService()
    }
    
    
    private fun makeInPlayerRemoteService(): InPlayerRemotePublicServiceAPI {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        retrofitAPI = buildService(okHttpClient)
        return retrofitAPI
    }
    
    private fun buildService(okHttpClient: OkHttpClient): InPlayerRemotePublicServiceAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(InPlayerRemotePublicServiceAPI::class.java)
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
        var builder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor {
                    customHeaderIntercepted(it)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        
        return buildOkHttpClient(builder)
        
    }
    
    
    private fun buildOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
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
     * AUTHENTICATION Endpoint Implementations
     * */
    
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String, metadata: HashMap<String, String>?) = retrofitAPI.createAccount(fullName, email, password, passwordConfirmation, type, merchantUUID, referrer, metadata)
    
    override fun authenticate(username: String?, password: String?, clientSecret: String?, refreshToken: String?, grantType: String, clientId: String) = retrofitAPI.authenticate(username, password, clientSecret, refreshToken, grantType, clientId)
    
    override fun setNewPassword(token: String, password: String, passwordConfirmation: String) = retrofitAPI.setNewPassword(token, password, passwordConfirmation)
    
    override fun forgotPassword(merchantUUID: String, email: String) = retrofitAPI.forgotPassword(merchantUUID, email)
    
    /**
     * END -> AUTHENTICATION Endpoint Implementations
     * */
    
    
    /**
     * ASSETS PUBLIC Endpoint Implementations
     * */
    
    override fun getItemDetails(id: Int, merchantUUID: String) = retrofitAPI.getItemDetails(id, merchantUUID)
    
    override fun getAccessFees(id: Int) = retrofitAPI.getAccessFees(id)
    
    /**
     * ASSETS PUBLIC Endpoint Implementations
     * */
    
}