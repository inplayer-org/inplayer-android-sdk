package com.sdk.inplayer.mapper.subscription

import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.legacy.InPlayerSubscribeSuccessNotificationResource

data class InPlayerSubscriptionSuccessNotification(
    val resource: InPlayerSubscribeSuccessNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotification

