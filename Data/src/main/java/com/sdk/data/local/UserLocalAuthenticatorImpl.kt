package com.sdk.data.local

import android.content.Context
import com.google.gson.Gson
import com.sdk.data.Constants
import com.sdk.data.local.PreferenceHelper.defaultPrefs
import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.repository.gateway.UserLocalAuthenticator


class UserLocalAuthenticatorImpl(context: Context) : UserLocalAuthenticator {
    
    val prefs = defaultPrefs(context)
    
    override fun saveAuthenticationToken(accessToken: String) {
        prefs.acccessToken = accessToken
    }
    
    override fun getAuthenticationToken(): String {
        return "${prefs.acccessToken}"
    }
    
    override fun getBearerAuthToken() = "${Constants.HttpHeaderBearerTokenPrefix} ${prefs.acccessToken}"
    
    
    override fun isUserAutehnticated(): Boolean {
        return prefs.acccessToken != null
    }
    
    override fun deleteAuthentiationToken() {
        prefs.acccessToken = null
    }
    
    override fun saveRefreshToken(refreshToken: String, expiresAt: Long) {
        prefs.refreshToken = refreshToken
        prefs.refreshTokenExpiresAt = expiresAt
    }
    
    override fun getRefreshToken(): String {
        return "${prefs.refreshToken}"
    }
    
    override fun deleteRefreshToken() {
        prefs.refreshToken = null
    }
    
    override fun deleteTokens() {
        prefs.refreshToken = null
        prefs.refreshTokenExpiresAt = 0
        prefs.acccessToken = null
    }
    
    override fun getExpiresAt() = prefs.refreshTokenExpiresAt
    
    override fun saveCurrentUser(inPlayerAccount: InPlayerAccount) {
        val currentUserJSON = Gson().toJson(inPlayerAccount)
        prefs.currentUser = currentUserJSON
    }
    
    override fun getAccount(): InPlayerAccount? {
        return Gson().fromJson<InPlayerAccount>(prefs.currentUser, InPlayerAccount::class.java)
    }
    
    override fun deleteCurrentUser() {
        prefs.currentUser = null
    }
    
}

