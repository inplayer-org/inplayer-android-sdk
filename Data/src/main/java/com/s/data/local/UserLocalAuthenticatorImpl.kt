package com.s.data.local

import android.content.Context
import com.google.gson.Gson
import com.s.data.Constants
import com.s.data.local.PreferenceHelper.defaultPrefs
import com.s.data.model.account.InPlayerAccount
import com.s.data.repository.gateway.UserLocalAuthenticator


/**
 * Created by victor on 12/25/18
 */
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
    
    override fun saveRefreshToken(refreshToken: String) {
        prefs.refreshToken = refreshToken
    }
    
    override fun getRefreshToken(): String {
        return "${prefs.refreshToken}"
    }
    
    override fun deleteRefreshToken() {
        prefs.refreshToken = null
    }
    
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

