package com.s.notification.model.legacy

import com.s.notification.model.notification.InPlayerNotification

/**
 * Created by victor on 1/16/19
 */
data class InPlayerSubscribeFailedNotification(val resource: InPlayerSubscribeFailedNotificationResource,
                                               override val type: String, override val timestamp: Long) : InPlayerNotification

data class InPlayerSubscribeFailedNotificationResource(
        val account_id: Int,
        val code: Int,
        val message: String
)
