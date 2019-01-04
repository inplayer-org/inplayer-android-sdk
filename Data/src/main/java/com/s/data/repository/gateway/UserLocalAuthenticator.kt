package com.s.data.repository.gateway

import io.reactivex.Single

/**
 * Created by victor on 12/25/18
 */
interface UserLocalAuthenticator {
    
    //Access Token
    fun saveAuthenticationToken(accessToken: String)
    
    fun getAuthenticationToken(): Single<String>
    
    fun getBearerAuthToken(): String
    
    fun isUserAutehnticated(): Boolean
    
    fun deleteAuthentiationToken()
    
    //Refresh Token
    fun saveRefreshToken(refreshToken: String)
    
    fun getRefreshToken(): String
    
    fun deleteRefreshToken()
    
}