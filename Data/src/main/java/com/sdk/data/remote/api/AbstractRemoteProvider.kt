package com.sdk.data.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by victor on 1/8/19
 *
 * NOT USED, Would be good to make one abstract class for
 * @see InPlayerRemoteProvider
 * @see InPlayerRemotePublicProvider
 *
 * but there were some problems with the depndenci injection.
 *
 */
abstract class AbstractRemoteProvider<T : Any>(val baseUrl: String, val isDebug: Boolean) {
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    lateinit var retrofitAPI: T
    
    init {
        makeInPlayerRemoteService()
    }
    
    abstract fun getRetrofitInterface(): Class<T>
    
    private fun makeInPlayerRemoteService(): T {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        retrofitAPI = buildService(okHttpClient)
        return retrofitAPI
    }
    
    private fun buildService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(getRetrofitInterface())
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
        
        builder = addCustomInterceptors(builder)
        
        return buildOkHttpClient(builder)
        
    }
    
    abstract fun addCustomInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder
    
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
}