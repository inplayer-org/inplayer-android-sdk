package com.sdk.inplayer.mapper.payment

import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.legacy.InPlayerExternalPaymentFailedNotificationResource

class InPlayerExternalPaymentFailedMapper(
    val resource: InPlayerExternalPaymentFailedNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotification