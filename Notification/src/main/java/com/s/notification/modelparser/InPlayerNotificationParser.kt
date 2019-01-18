package com.s.notification.modelparser

import com.google.gson.GsonBuilder
import com.s.notification.model.notification.InPlayerNotification


/**
 * Created by victor on 1/16/19
 */
object InPlayerNotificationParser {
    
    fun parseJSON(jsonString: String): InPlayerNotification {
        
        val gson = GsonBuilder().registerTypeAdapter(InPlayerNotification::class.java, InPlayerNotificationDeserializer()).create()
        
        return gson.fromJson(jsonString, InPlayerNotification::class.java)
    }
}