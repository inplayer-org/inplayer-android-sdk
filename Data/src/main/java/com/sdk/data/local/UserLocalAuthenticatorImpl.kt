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
        prefs.acccessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhaWQiOjIxNjQwNjcsImF1ZCI6IjBlYWY0YmJhLWUyNGEtNGMzZS1iZTBlLWE5ZjhiZTQ1Nzg3MyIsImN0eCI6WyJjb25zdW1lciJdLCJleHAiOjE2MDMzOTUyNDcsImlhdCI6MTYwMDc2MTg0NywianRpIjoiMzBlNDIzZTctOWFmMC00MTAyLThhZGEtM2FhMGI5YzdhOWZjIiwibWlkIjoyMDY2NzQ4LCJtdWkiOiIwZWFmNGJiYS1lMjRhLTRjM2UtYmUwZS1hOWY4YmU0NTc4NzMiLCJuYmYiOjE2MDA3NjE4NDcsIm9pZCI6MCwic2NvcGVzIjpbXSwic3ViIjoiYnJpYW5tQGZvb3RiYWxsbnN3LmNvbS5hdSIsInRpZCI6MjE2NDA2NywidHV1aWQiOiIzMGU0MjNlNy05YWYwLTQxMDItOGFkYS0zYWEwYjljN2E5ZmMifQ.-eSi7KiYlMnFyE4W8owLQqzY8y65tbqsjdwLOloFhaQ"
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

