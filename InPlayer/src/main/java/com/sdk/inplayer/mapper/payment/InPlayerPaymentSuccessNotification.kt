package com.sdk.inplayer.mapper.payment

import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.legacy.InPlayerPaymentCardSuccessNotificationResource

data class InPlayerPaymentSuccessNotification(
    val resource: InPlayerPaymentCardSuccessNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotification

