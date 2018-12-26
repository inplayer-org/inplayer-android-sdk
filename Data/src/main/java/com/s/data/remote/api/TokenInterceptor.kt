package com.s.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by victor on 12/26/18
 */
class TokenInterceptor() : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "MyauthHeaderContent")
        
        return chain.proceed(builder.build())
    }
}