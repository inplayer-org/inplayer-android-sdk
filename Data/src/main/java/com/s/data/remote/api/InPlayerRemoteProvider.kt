package com.s.data.remote.api

import com.s.data.remote.error.AuthTokenMissingException
import com.s.data.remote.refresh_token.RefreshAuthenticator
import com.s.data.repository.gateway.UserLocalAuthenticator
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
 *
 * This Remote Providers attaches two interceptors
 * @see RefreshAuthenticator
 *  This one is used to refresh the token if our current token is expired.
 *
 * @see InPlayerRemoteProvider.addAuthorizationHeader()
 *  This interceptor allows us to check if we have AuthToken stored in our
 *  @see UserLocalAuthenticator if the user is not authenticated we will return error without even trying to reach the API
 *
 */
class InPlayerRemoteProvider(val baseUrl: String,
                             val isDebug: Boolean,
                             val refreshAuthenticator: RefreshAuthenticator,
                             val localAuthenticator: UserLocalAuthenticator) : InPlayerRemoteServiceAPI {
    
    /**
     * Creating Retrofit and setting up Logging
     * */
    
    lateinit var retrofitAPI: InPlayerRemoteServiceAPI
    
    init {
        makeInPlayerRemoteService()
    }
    
    private fun makeInPlayerRemoteService(): InPlayerRemoteServiceAPI {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        retrofitAPI = buildService(okHttpClient)
        return retrofitAPI
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
     * Creating Retrofit and setting up Logging
     * */
    
    
    private fun addAuthorizationHeader(chain: Interceptor.Chain): Response? {
        if (!localAuthenticator.isUserAutehnticated())
            throw AuthTokenMissingException("User is not Authenticated")
        
        val original = chain.request()
        val request = original.newBuilder()
                .header("Authorization", localAuthenticator.getBearerAuthToken())
                .method(original.method(), original.body())
                .build()
        return chain.proceed(request)
    }
    
    fun addCustomInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.authenticator(refreshAuthenticator)
        builder.addInterceptor {
            addAuthorizationHeader(it)
        }
        return builder
    }
    
    
    /**
     * END -> Creating Retrofit and setting up Logging
     * */
    
    
    /**
     * ACCOUNT Endpoint Implementations
     * */
    
    override fun logout() = retrofitAPI.logout()
    
    override fun getAccount() = retrofitAPI.getAccount()
    
    override fun updateAccount(fullName: String, metadata: HashMap<String, String>?) = retrofitAPI.updateAccount(fullName, metadata)
    
    override fun eraseAccount(password: String) = retrofitAPI.eraseAccount(password)
    
    override fun changePassword(password: String, passwordConfirmation: String, oldPassword: String) = retrofitAPI.changePassword(password, passwordConfirmation, oldPassword)
    
    
    /**
     * END -> ACCOUNT Endpoint Implementations
     * */
    
    
    /**
     * ASSETS Endpoint Implementations
     * */
    
    override fun getItemAccess(id: Int) = retrofitAPI.getItemAccess(id)
    
    
    /**
     * END -> ASSETS Endpoint Implementations
     * */
}