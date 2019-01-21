package com.s.notification.modelparser

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.s.notification.model.legacy.InPlayerPaymentCardFailedNotifcation
import com.s.notification.model.legacy.InPlayerPaymentCardSuccessNotifcation
import com.s.notification.model.legacy.InPlayerSubscribeFailedNotification
import com.s.notification.model.legacy.InPlayerSubscribeSuccessNotification
import com.s.notification.model.notification.*
import java.lang.reflect.Type

/**
 * Created by victor on 1/16/19
 */
class InPlayerNotificationDeserializer : JsonDeserializer<InPlayerNotificationEntity> {
    
    var notificationType = hashMapOf(
            "payment.card.success" to InPlayerPaymentCardSuccessNotifcation::class.java,
            "payment.card.failed" to InPlayerPaymentCardFailedNotifcation::class.java,
            "subscribe.success" to InPlayerSubscribeSuccessNotification::class.java,
            "subscribe.failed" to InPlayerSubscribeFailedNotification::class.java,
            "access.granted" to InPlayerAccessGrantedNotification::class.java,
            "access.revoked" to InPlayerAccessRevokedNotification::class.java,
            
            "account.logout" to InPlayerAccountLogoutNotificationEntity::class.java,
            "account.erased" to InPlayerAccountErasedNotificationEntity::class.java,
            "account.deactivated" to InPlayerAccountDeactivatedNotificationEntity::class.java
    )
    
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): InPlayerNotificationEntity {
        json?.let {
            val notificationJson = it.asJsonObject
            val type = notificationJson.get("type").asString
            
            val classType = notificationType[type]
            
            return Gson().fromJson(json, classType)
        }
        
    }
    
}