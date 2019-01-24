package com.sdk.notification.modelparser

import com.google.gson.GsonBuilder
import com.sdk.notification.model.notification.InPlayerNotificationEntity


object InPlayerNotificationParser {
    
    fun parseJSON(jsonString: String): InPlayerNotificationEntity {
        
        val gson = GsonBuilder().registerTypeAdapter(InPlayerNotificationEntity::class.java, InPlayerNotificationDeserializer()).create()
        
        return gson.fromJson(jsonString, InPlayerNotificationEntity::class.java)
    }
}