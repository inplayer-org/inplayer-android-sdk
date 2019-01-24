package com.sdk.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.sdk.data.Constants



object PreferenceHelper {
    
    lateinit var prefs: SharedPreferences
    
    
    fun defaultPrefs(context: Context): PreferenceHelper {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return this
    }
    
    var acccessToken: String?
        get() = prefs.getString(Constants.SharedPrefsKey.AccessToken, null)
        set(value) = prefs.edit().putString(Constants.SharedPrefsKey.AccessToken, value).apply()
    
    var refreshToken: String?
        get() = prefs.getString(Constants.SharedPrefsKey.RefreshToken, null)
        set(value) = prefs.edit().putString(Constants.SharedPrefsKey.RefreshToken, value).apply()
    
    var currentUser: String?
        get() = prefs.getString(Constants.SharedPrefsKey.CurrentUser, null)
        set(value) = prefs.edit().putString(Constants.SharedPrefsKey.CurrentUser, value).apply()
    
}