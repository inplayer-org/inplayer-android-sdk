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
 * Created by victor on 12/21/18
 */
class InPlayerRemoteProvider(private val baseUrl: String, private val isDebug: Boolean) : InPlayerRemoteServiceAPI {
    
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    
    private lateinit var inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI
    
    init {
        makeInPlayerRemoteService()
    }
    
    private fun makeInPlayerRemoteService(): InPlayerRemoteServiceAPI {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        inPlayerRemoteServiceAPI = buildService(okHttpClient)
        return inPlayerRemoteServiceAPI
    }
    
    private fun buildService(okHttpClient: OkHttpClient): InPlayerRemoteServiceAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(InPlayerRemoteServiceAPI::class.java)
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
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
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
     * END ->> Creating Retrofit and setting up Logging
     * */
    
    
    /**
     * Endpoint Implementations
     * */
    override fun createAccount(email: String, fullName: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String) = inPlayerRemoteServiceAPI.createAccount(email, fullName, password, passwordConfirmation, type, merchantUUID)
    
    override fun authenticate(username: String, password: String, grantType: String, clientId: String) = inPlayerRemoteServiceAPI.authenticate(username, password, grantType, clientId)
    
}