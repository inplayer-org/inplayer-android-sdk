package com.sdk.notification.modelparser

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sdk.notification.model.legacy.*
import com.sdk.notification.model.notification.*
import java.lang.reflect.Type


class InPlayerNotificationDeserializer : JsonDeserializer<InPlayerNotificationEntity> {
    
    var notificationType = hashMapOf(
        "payment.card.success" to InPlayerPaymentCardSuccessNotifcation::class.java,
        "payment.card.failed" to InPlayerPaymentCardFailedNotification::class.java,
        "subscribe.success" to InPlayerSubscribeSuccessNotification::class.java,
        "subscribe.failed" to InPlayerSubscribeFailedNotification::class.java,
        "subscribe.cancelled.success" to InPlayerSubscribeCancelledSuccessNotification::class.java,

        "access.granted" to InPlayerAccessGrantedNotificationEntity::class.java,
        "access.revoked" to InPlayerAccessRevokedNotification::class.java,
        
        "account.logout" to InPlayerAccountLogoutNotificationEntity::class.java,
        "account.erased" to InPlayerAccountErasedNotificationEntity::class.java,
        "account.deactivated" to InPlayerAccountDeactivatedNotificationEntity::class.java,


        "external.payment.success" to InPlayerExternalPaymentSuccessNotification::class.java,
        "external.subscribe.cancel.success" to InPlayerExternalSubscriberCancelNotification::class.java,
        "external.payment.failed" to InPlayerExternalPaymentFailedNotification::class.java
    )
    
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): InPlayerNotificationEntity {
        json?.let {
            val notificationJson = it.asJsonObject
            val type = notificationJson.get("type").asString
            
            val classType = notificationType[type]
            
            return Gson().fromJson(json, classType)
        }
        throw IllegalStateException("InPlayerNotificationDeserializer unable to parse! JsonElement is null ")
    }
    
}