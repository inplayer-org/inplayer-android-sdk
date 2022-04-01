package com.sdk.inplayer.mapper.payment

import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.legacy.InPlayerExternalPaymentSuccessNotificationResource

data class InPlayerExternalPaymentSuccessNotificationMapper(
    val resource: InPlayerExternalPaymentSuccessNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotification