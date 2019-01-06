package com.s.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.s.data.Constants


/**
 * Created by victor on 12/25/18
 */
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
}