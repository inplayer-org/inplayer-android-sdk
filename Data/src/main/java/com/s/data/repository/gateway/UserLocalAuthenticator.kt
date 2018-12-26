package com.s.data.repository.gateway

import io.reactivex.Single

/**
 * Created by victor on 12/25/18
 */
interface UserLocalAuthenticator {
    
    fun saveAuthenticationToken(accessToken: String)
    
    fun getAuthenticationToken(): Single<String>
    
    fun getBearerAuthToken() : String
    
    fun isUserAutehnticated(): Boolean
    
    fun deleteAuthentiationToken()
    
}