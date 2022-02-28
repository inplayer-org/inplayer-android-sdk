package com.sdk.inplayer.mapper.payment

import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.legacy.InPlayerExternalSubscriberCancelNotificationResource

data class InPlayerExternalSubscriberCancelMapper(
    val resource: InPlayerExternalSubscriberCancelNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotification