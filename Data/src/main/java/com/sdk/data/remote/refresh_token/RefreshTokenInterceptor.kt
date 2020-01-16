package com.sdk.data.remote.refresh_token

import android.util.Log
import com.sdk.data.Constants
import com.sdk.data.repository.gateway.UserLocalAuthenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

private const val TAG = "RefreshTokenInterceptor"
private const val MAX_RETRY = 2

class RefreshTokenInterceptor(private val clientId: String,
                              private val localAuthenticator: UserLocalAuthenticator,
                              private val inPlayerRemoteRefreshServiceAPI: InPlayerRemoteRefreshServiceAPI): Interceptor{
    
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code() == 401 || response.code() == 403) {
            return handle40XRequest(chain)
        }
        return response
    }
    
    private fun handle40XRequest(chain: Interceptor.Chain): Response?{
        val request = chain.request()
        val response = chain.proceed(request)
        when (hasBearerAuthorizationToken(response)) {
            false -> {
                // No bearer auth token; nothing to refresh!
                localAuthenticator.deleteTokens()
                Log.d(TAG, "No bearer authentication to refresh.")
                return null
            }
            true -> {
                // It had a bearer auth!
                Log.d(TAG, "Bearer authentication present!")
                val previousRetryCount = retryCount(response)
                // Attempt to reauthenticate using the refresh token!
                val request = reAuthenticateRequestUsingRefreshToken(
                    response,
                    previousRetryCount + 1
                )
                return chain.proceed(request)
            }
        }
    }
    
    // We synchronize this request, so that multiple concurrent failures
    // don't all try to use the same refresh token!
    @Synchronized
    private fun reAuthenticateRequestUsingRefreshToken(
        staleRequest: Response,
        retryCount: Int
    ): Request? {
        
        // If the token is not expired do not retry the logic clear all the credentials and return
        if(!isTokenExpired()){
            Log.d(TAG, "Token is not expired, but overridden somewhere. Deleting tokens ")
            localAuthenticator.deleteTokens()
            return null
        }
        
        // See if we have gone too far:
        if (retryCount > MAX_RETRY) {
            // Yup!
            Log.d(TAG, "Retry count exceeded! Giving up.")
            // Don't try to re-authenticate any more.
            return null
        }
        
        // We have some retries left!
        Log.d(TAG, "Attempting to fetch a new token...")
        // Could not retrieve new token! Unable to re-authenticate!
        if (localAuthenticator.getRefreshToken().isEmpty()) {
            localAuthenticator.deleteTokens()
            Log.d(TAG, "Failed to retrieve new token, unable to re-authenticate!")
            return null
        }
        
        if (isBearerAuthorizationTokenDifferent(staleRequest))
            makeRefreshTokenRequest()
        
        // Try for the new token:
        Log.d(TAG, "Retreived new token, re-authenticating...")
        return rewriteRequest(
            staleRequest.request(),
            retryCount,
            localAuthenticator.getBearerAuthToken()
        )
    }
    
    
    private fun makeRefreshTokenRequest() {
        Log.d(TAG, "Creating new Refresh Token Request")
        val refreshTokenCall = inPlayerRemoteRefreshServiceAPI.authenticate(
            localAuthenticator.getRefreshToken(),
            "refresh_token",
            clientId
        )
        val request = refreshTokenCall.execute()
        val authorizationModelResponse = request.body()
        if (authorizationModelResponse != null) {
            Log.d(TAG, "Creating new Refresh SUCCESS!! $authorizationModelResponse")
            localAuthenticator.saveAuthenticationToken(authorizationModelResponse.accessToken)
            localAuthenticator.saveRefreshToken(
                authorizationModelResponse.refreshToken,
                authorizationModelResponse.expires
            )
        } else
            Log.d(TAG, "Creating new Refresh FAILED!!")
    }
    
    private fun rewriteRequest(
        staleRequest: Request?, retryCount: Int, authToken: String
    ): Request? {
        return staleRequest?.newBuilder()
            ?.header(
                Constants.HttpHeaderAuthorization,
                authToken
            )
            ?.header(
                Constants.HttpHeaderRetryCount,
                "$retryCount"
            )
            ?.build()
    }
    
    private fun retryCount(response: Response?): Int {
        return response?.request()?.header(Constants.HttpHeaderRetryCount)?.toInt() ?: 0
    }
    
    private fun hasBearerAuthorizationToken(response: Response?): Boolean {
        response?.let { response ->
            val authorizationHeader = response.request().header(Constants.HttpHeaderAuthorization)
            return authorizationHeader.startsWith(Constants.HttpHeaderBearerTokenPrefix)
        }
        return false
    }
    
    private fun isBearerAuthorizationTokenDifferent(response: Response?): Boolean {
        response?.let { response ->
            val authorizationHeader = response.request().header(Constants.HttpHeaderAuthorization)
            return authorizationHeader == localAuthenticator.getBearerAuthToken()
        }
        return false
    }
    
    private fun isTokenExpired(): Boolean {
        val dateNow = Date(System.currentTimeMillis())
        val dateThen = Date(localAuthenticator.getExpiresAt() * 1000)
        return dateNow.after(dateThen)
    }
}
