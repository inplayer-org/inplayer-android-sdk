package com.s.data.remote.refresh_token

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by victor on 1/3/19
 */
class InPlayerRemoteRefreshTokenProvider(private val baseUrl: String, private val isDebug: Boolean) : InPlayerRemoteRefreshServiceAPI {
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    private lateinit var inPlayerRemoteRefreshServiceAPI: InPlayerRemoteRefreshServiceAPI
    
    init {
        makeInPlayerRemoteProvider()
    }
    
    private fun makeInPlayerRemoteProvider(): InPlayerRemoteRefreshServiceAPI {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        inPlayerRemoteRefreshServiceAPI = buildService(okHttpClient)
        return inPlayerRemoteRefreshServiceAPI
    }
    
    private fun buildService(okHttpClient: OkHttpClient): InPlayerRemoteRefreshServiceAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(InPlayerRemoteRefreshServiceAPI::class.java)
    }
    
    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BASIC
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
    
    
    override fun authenticate(refreshToken: String, grantType: String, clientId: String) = inPlayerRemoteRefreshServiceAPI.authenticate(refreshToken, grantType, clientId)
}