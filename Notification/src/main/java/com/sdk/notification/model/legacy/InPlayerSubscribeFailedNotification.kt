package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity

data class InPlayerSubscribeFailedNotification(val resource: InPlayerSubscribeFailedNotificationResource,
                                               override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerSubscribeFailedNotificationResource(
        val account_id: Int,
        val code: Int,
        val message: String
)
