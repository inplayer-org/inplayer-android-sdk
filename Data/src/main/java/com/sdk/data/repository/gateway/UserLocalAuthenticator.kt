package com.sdk.data.repository.gateway

import com.sdk.data.model.account.InPlayerAccount


interface UserLocalAuthenticator {
    
    //Access Token
    fun saveAuthenticationToken(accessToken: String)
    
    fun getAuthenticationToken(): String
    
    fun getBearerAuthToken(): String
    
    fun isUserAutehnticated(): Boolean
    
    fun deleteAuthentiationToken()
    
    //Refresh Token
    fun saveRefreshToken(refreshToken: String, expiresAt: Long)
    
    fun getRefreshToken(): String
    
    fun deleteRefreshToken()
    
    fun getExpiresAt(): Long
    
    
    //Current User
    fun saveCurrentUser(inPlayerAccount: InPlayerAccount)
    
    fun getAccount(): InPlayerAccount?
    
    fun deleteCurrentUser()
    
}